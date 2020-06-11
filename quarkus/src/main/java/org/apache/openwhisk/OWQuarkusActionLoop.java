package org.apache.openwhisk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import org.apache.openwhisk.sample.GreetingMain;

import javax.inject.Inject;
import java.io.*;
import java.util.Scanner;


public class OWQuarkusActionLoop implements QuarkusApplication {

    @Inject
    OWMainInterface service;

    @Override
    public int run(String... args) throws Exception {

        boolean debugMode = false;

        if(args.length>0)
            System.out.println(String.join(" ", args));

        // exit after main class loading if "exit" specified
        // used to check healthy launch after init
        if(args.length >1 && args[1] == "-exit")
            System.exit(0);


        Scanner sc = new Scanner(System.in);
        String activeProfile = io.quarkus.runtime.configuration.ProfileManager.getActiveProfile();
        PrintWriter out;
        if (activeProfile.equalsIgnoreCase("dev")) {
            out = new PrintWriter(System.out);
        } else if (activeProfile.equalsIgnoreCase("test")) {
            out = new PrintWriter(System.out);
        } else {
            // Production mode when used with the Action Proxy
            out = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("/dev/fd/3"), "UTF-8"));
        }

        System.err.println("Send the ACK to let OW know the action is ready");
        // Send the ACK to let OW know the action is ready
        out.println("{ \"ok\": true }");
        out.flush();
        String input = "";

        while (sc.hasNextLine()) {
            try {
                input = sc.nextLine();
                if (input == null) {
                    System.out.println("input was null, ignoring");
                    continue;
                } else if (input.equals("")) {
                    System.out.println("input was empty, exiting");
                    break;
                }

                JsonNode rootNode = OWJSONHelper.parseJSON(input);
                JsonNode debugNode = rootNode.at("/__ow_debug");
                if (!debugNode.isEmpty())
                    debugMode = debugNode.asBoolean();

                debugLog(debugMode, input);

                // TODO What about env variables passed as __OW_ ?

                out.println(service.run( OWJSONHelper.getValueNode(rootNode) ).toString());
            } catch (NullPointerException npe) {
                System.out.println("the action returned null");
                constructAndSeSetException(out, npe);

            } catch (Exception e) {
                System.out.println("the action encountered an error");
                constructAndSeSetException(out, e);
            }
            out.flush();
            System.out.flush();
            System.err.flush();
        }
        return 0;

    }

        private void debugLog(boolean debugMode, String logMessage) {
            if (debugMode)
                System.out.println("__ow_debug: " + logMessage);
        }

        private void constructAndSeSetException(PrintWriter out, Exception e) {
            e.printStackTrace(System.err);
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode rootNode = mapper.createObjectNode();

            if (e instanceof NullPointerException) {
                rootNode.put("error", "the action returned null");
            } else if (e instanceof NullPointerException) {
                rootNode.put("error", e.getMessage());
            }

            out.println(rootNode.toString());
            out.flush();
        }
}
