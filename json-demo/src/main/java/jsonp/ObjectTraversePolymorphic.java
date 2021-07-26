package jsonp;

import java.io.FileNotFoundException;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 * Traverse (Polymorphic) demo
 *
 * @version 1.0
 */
public class ObjectTraversePolymorphic {

    public static void main(String... args) throws FileNotFoundException {
        new ObjectTraversePolymorphic().printValue(Utils.loadJsonObject());
    }

    /**
     * Delegates processing to appropriate printValue method based on type
     *
     * @param key       String key value
     * @param jsonValue JsonValue type
     */
    private void printValue(String key, JsonValue jsonValue) {
        if (key != null) printKey(key);

        if (jsonValue instanceof JsonArray) {
            printValue((JsonArray) jsonValue);
            return;
        }
        if (jsonValue instanceof JsonObject) {
            printValue((JsonObject) jsonValue);
            return;
        }
        if (jsonValue instanceof JsonNumber) {
            printValue((JsonNumber) jsonValue);
            return;
        }
        if (jsonValue instanceof JsonString) {
            printValue((JsonString) jsonValue);
            return;
        }
        printValue(jsonValue.toString());
    }

    /**
     * Processes Array types
     *
     * @param jsonValues JsonArray type
     */
    private void printValue(JsonArray jsonValues) {
        jsonValues.forEach(jsonValue -> printValue(null, jsonValue));
    }

    /**
     * Processes JsonObject types
     *
     * @param jsonObject JsonObject type
     */
    private void printValue(JsonObject jsonObject) {
        jsonObject.forEach(this::printValue);
    }

    /**
     * Prints the JsonString value
     *
     * @param jsonString the JsonString value
     */
    private void printValue(JsonString jsonString) {
        printValue(jsonString.getString());
    }

    /**
     * Prints the JsonNumber value
     *
     * @param jsonNumber the JsonNumber value
     */
    private void printValue(JsonNumber jsonNumber) {
        printValue(jsonNumber.toString());
    }

    /**
     * Prints the String value to the console
     *
     * @param value the value to print
     */
    private void printValue(String value) {
        System.out.println(value);
    }

    /**
     * Prints the key name
     *
     * @param key the key name
     */
    private void printKey(String key) {
        System.out.print(key + ": ");
    }
}
