package jsonp;

import java.io.FileNotFoundException;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * @version 1.0
 */
public class Utils {

    public static final String ROOT = "resources";

    public static final String JSON =
            "{\n" +
            "  \"title\": \"JSON-Processing With Java EE\",\n" +
            "  \"chapters\": [\n" +
            "    \"Introduction\",\n" +
            "    \"1. JSON and Java\",\n" +
            "    \"2. JSON-Processing API features\",\n" +
            "    \"3. The Java EE JSON Object model\",\n" +
            "    \"4. The Java EE JSON Streaming model\",\n" +
            "    \"Conclusion\"\n" +
            "  ],\n" +
            "  \"released\": true,\n" +
            "  \"length\": 90,\n" +
            "  \"sourceCode\": {\n" +
            "    \"repositoryName\": \"JSON-Processing-with-Java-EE\",\n" +
            "    \"url\": \"github.com/readlearncode\"\n" +
            "  },\n" +
            "  \"complementaryCourse\": [\n" +
            "    {\n" +
            "      \"title\": \"RESTful Service with JAX-RS 2.0\",\n" +
            "      \"length\": 120\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Java Enterprise Edition Introduction\",\n" +
            "      \"length\": 130\n" +
            "    }\n" +
            "  ],\n" +
            "  \"notes\": null\n" +
            "}";

    public static JsonObject buildJsonDocument() {

        JsonObject jsonObject =
                Json.createObjectBuilder()
                    .add("title", "JSON-Processing With Java EE")
                    .add("chapters",
                         Json.createArrayBuilder()
                             .add("Introduction")
                             .add("1. JSON and Java")
                             .add("2. JSON-Processing API features")
                             .add("3. The Java EE JSON Object model")
                             .add("4. The Java EE JSON Streaming model")
                             .add("Conclusion"))
                    .add("released", true)
                    .add("length", 90)
                    .add("sourceCode",
                         Json.createObjectBuilder()
                             .add("repositoryName", "JSON-Processing-with-Java-EE")
                             .add("url", "github.com/readlearncode"))
                    .add("complementaryCourse",
                         Json.createArrayBuilder()
                             .add(Json.createObjectBuilder()
                                      .add("title",
                                           "RESTful Service with JAX-RS 2.0")
                                      .add("length", 120))
                             .add(Json.createObjectBuilder()
                                      .add("title",
                                           "Java Enterprise Edition Introduction")
                                      .add("length", 130)))
                    .addNull("notes")
                    .build();

        return jsonObject;
    }

    /**
     * Load JSON data from a flat-file
     *
     * @return a JsonObject that models the JSON data in the flat-file
     * @throws FileNotFoundException
     */
    public static JsonObject loadJsonObject() throws FileNotFoundException {
        return Json.createReader(
                ObjectTraverseDemo.class.getResourceAsStream("/jsondata-object.json"))
                   .readObject();
    }
}
