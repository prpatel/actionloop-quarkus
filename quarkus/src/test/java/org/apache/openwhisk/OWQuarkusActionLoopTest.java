package org.apache.openwhisk;

import io.quarkus.runtime.Quarkus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;

public class OWQuarkusActionLoopTest {

    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;

    private static ByteArrayOutputStream testOut;
    private static PipedOutputStream in = new PipedOutputStream();

    @BeforeAll
    public static void setUpOutput() throws IOException {
        System.err.println("Redirecting System.out to buffer");
        testOut = new ByteArrayOutputStream();

        System.setIn(new PipedInputStream(in));
        PrintStream outPrintStream = new PrintStream(testOut);
        System.setOut(outPrintStream);
        Main.main(new String[]{});
    }

    private void provideInput(String data) throws IOException {
        in.write(data.getBytes("utf-8"));
    }

    private String getOutput() {
        String outputString = testOut.toString();
        testOut.reset();
        return outputString;
    }

    private String getOneLineOutput() {
        String result = "";
        boolean notFoundEOL = true;
        while(notFoundEOL) {
            result = testOut.toString();
            if (result.indexOf('\n') > 0) {
                notFoundEOL = false;
            }
        }
        return result;
    }

    @AfterAll
    public static void restoreSystemInputOutput() {
        System.err.println("restoreSystemInputOutput");
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testActionLoop() throws IOException, InterruptedException {
        // clear System.out buffer
        getOutput();
        String data = "{\"value\":{\"name\":\"Mike\"}, \"__ow_debug\":true}\n";
        provideInput(data);
        String expectedResult = "{\"greeting\":\"hello Mike\"}\n";
        Assertions.assertEquals(expectedResult, getOneLineOutput());
        provideInput("\n");
        Quarkus.asyncExit();
    }

}
