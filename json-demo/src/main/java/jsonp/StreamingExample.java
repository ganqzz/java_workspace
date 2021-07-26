package jsonp;

import java.io.StringReader;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

import static javax.json.stream.JsonParser.Event.VALUE_NUMBER;
import static javax.json.stream.JsonParser.Event.VALUE_STRING;

/**
 * @version 1.0
 */
public class StreamingExample {

    public String retrieveValue(final String key_to_find, final String json) {
        JsonParser parser = Json.createParser(new StringReader(json));

        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
            case KEY_NAME:
                if (parser.getString().equalsIgnoreCase(key_to_find)) {
                    event = parser.next();
                    if (event == VALUE_STRING || event == VALUE_NUMBER) {
                        return parser.getString();
                    }
                }
                break;
            }
        }
        return null;
    }

    public String writeJsonStreamToString() {
        StringWriter stringWriter = new StringWriter();

        JsonGenerator generator = Json.createGenerator(stringWriter);
        generator.writeStartObject()
                 .write("title", "JSON-Processing With Java EE")
                 .writeStartArray("chapters")
                 .write("Introduction")
                 .write("1. JSON and Java")
                 .write("2. JSON-Processing API features")
                 .write("3. The Java EE JSON Object model")
                 .write("4. The Java EE JSON Streaming model")
                 .write("Conclusion")
                 .writeEnd()
                 .write("released", JsonValue.TRUE)
                 .write("length", 90)
                 .writeStartObject("sourceCode")
                 .write("repositoryName", "JSON-Processing-with-Java-EE")
                 .write("url", "github.com/readlearncode")
                 .writeEnd()
                 .writeStartArray("complementaryCourse")
                 .writeStartObject()
                 .write("title", "RESTful Service with JAX-RS 2.0")
                 .write("length", 120)
                 .writeEnd()
                 .writeStartObject()
                 .write("title", "Java Enterprise Edition Introduction")
                 .write("length", 130)
                 .writeEnd()
                 .writeEnd()
                 .write("notes", JsonValue.NULL)
                 .writeEnd();
        generator.close();

        return stringWriter.toString();
    }
}
