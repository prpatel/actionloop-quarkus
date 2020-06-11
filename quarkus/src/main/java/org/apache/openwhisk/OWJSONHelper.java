package org.apache.openwhisk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OWJSONHelper {

    public static JsonNode parseJSON(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = JsonNodeFactory.instance.objectNode();
        try {
            rootNode = objectMapper.readTree(jsonString);
        }  catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rootNode;
    }

    public static JsonNode getValueNode(JsonNode node) {
        return node.at("/value");
    }

}
