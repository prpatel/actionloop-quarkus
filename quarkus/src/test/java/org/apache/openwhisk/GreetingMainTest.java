package org.apache.openwhisk;

import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.openwhisk.sample.GreetingMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class GreetingMainTest {

    @Inject
    GreetingMain main;

    @Test
    public void testMain() throws Exception {
        String data = "{\"value\":{\"name\":\"Mike\"}, \"__ow_debug\":true}\n";
        JsonNode node = OWJSONHelper.parseJSON(data);
        JsonNode resultNode =  main.run(OWJSONHelper.getValueNode(node));

        String expectedResult = "{\"greeting\":\"hello Mike\"}\n";
        JsonNode expectedNode = OWJSONHelper.parseJSON(expectedResult);
        Assertions.assertEquals(expectedNode, resultNode);
    }
}
