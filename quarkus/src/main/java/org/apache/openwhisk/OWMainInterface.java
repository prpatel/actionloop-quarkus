package org.apache.openwhisk;

import com.fasterxml.jackson.databind.JsonNode;

public interface OWMainInterface {
    public JsonNode run(JsonNode rootNode) throws Exception;
}
