package org.apache.openwhisk.sample;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.openwhisk.OWMainInterface;

@ApplicationScoped
public class GreetingMain implements OWMainInterface {

    @Inject
    GreetingService service;

    @Override
    public JsonNode run(JsonNode rootNode) throws Exception {

        String name = rootNode.at("/name").asText();
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.put("greeting", service.greeting(name));
        return response;
    }


}
