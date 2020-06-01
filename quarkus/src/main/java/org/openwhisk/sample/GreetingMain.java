package org.openwhisk.sample;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.openwhisk.OWJSONHelper;

@ApplicationScoped
public class GreetingMain {

    @Inject
    GreetingService service;

    public JsonNode run(JsonNode rootNode) throws Exception {

        String name = rootNode.at("/message").asText();
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.put("greeting", "Hello "+name);
        return response;
    }


}
