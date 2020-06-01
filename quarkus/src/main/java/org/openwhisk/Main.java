package org.openwhisk;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.io.*;

@QuarkusMain
public class Main {
    public static void main(String... args) throws Exception {

//        String[] pathnames;
//
//        // Creates a new File instance by converting the given pathname string
//        // into an abstract pathname
//        File f = new File("/dev/fd/");
//
//        // Populates the array with names of files and directories
//        pathnames = f.list();
//
//        // For each pathname in the pathnames array
//        for (String pathname : pathnames) {
//            // Print the names of files and directories
//            System.err.println(pathname);
//        }
//
//        PrintWriter out = new PrintWriter(
//                new OutputStreamWriter(
//                        new FileOutputStream("/dev/fd/3"), "UTF-8"));
//        out.println("{ \"ok\": true }");
//        out.flush();
        Quarkus.run(OWQuarkusActionLoop.class, args);
    }
}
