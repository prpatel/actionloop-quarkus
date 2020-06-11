# ActionLoop Implementation for Quarkus

This is an OpenWhisk ActionLoop implementation for Quarkus. It implements a container that bundles Quarkus 1.4.2 libraries and allows easy deployment of Quarkus applications for OpenWhisk. Some notes:

* The Quarkus application is using Command Mode - not a standard http Quarkus application (lower overhead, memory usage, and faster startup time)
* Jackson is the JSON helper lib utilized

This is currently BETA software!


To get started:

`cd quarkus`

`mvn package`

`ibmcloud fn action create QCLITest1 target/quarkus-openwhisk-action-1.0.0-SNAPSHOT-runner.jar --docker actionloop-quarkus-1.4.2:latest`

`ibmcloud fn action invoke QCLITest1 --result`

## To update or build the base container

`cd quarkus`

Update the pom.xml to add your libs:

`mvn package`

Edit the Makefile with your Docker prefix & image name

`make push`

Test locally

`make start`

`make test-bin-zip`

## Creating your own project from the template Quarkus project

Again, this project uses Quarkus Command Mode. Go into  
`quarkus/src/main/java/org/openwhisk/sample/GreetingMain.java`
to see an example.

You can use another class for your application, just go to: `OWQuarkusActionLoop.java` and inject in your class - it should have a method with the following signature and return a ObjectNode or JsonNode:

    public JsonNode run(JsonNode rootNode) throws Exception {
        String name = rootNode.at("/message").asText();
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.put("greeting", "GreetingMain: " + service.greeting(name));
        return response;
    }

Remember to do a `mvn package` to update the JAR file when you get ready to deploy into OpenWhisk!




