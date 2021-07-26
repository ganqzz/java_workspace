package jsonp;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.StringReader;

/**
 * @version 1.0
 */
public class StreamingParseDemo {

    public static void main(String... args) {
        parseJsonString();
    }

    private static void parseJsonString() {
        JsonParser parser = Json.createParser(new StringReader(Utils.JSON));

        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case START_ARRAY:
                case END_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case VALUE_FALSE:
                case VALUE_NULL:
                case VALUE_TRUE:
                    System.out.println(event.toString());
                    break;
                case KEY_NAME:
                    System.out.print(event.toString() + " " + parser.getString() + " - ");
                    break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                    System.out.println(event.toString() + " " + parser.getString());
                    break;
            }
        }
    }
}
