package jsonp;

import java.io.FileNotFoundException;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 * Traverse (Polymorphic) with layout demo
 *
 * @version 1.0
 */
public class ObjectTraversePolymorphicWithLayout {

    private String valueIndent = "";
    private String keyIndent = "";
    private String separator = " : ";

    public static void main(String... args) throws FileNotFoundException {
        new ObjectTraversePolymorphicWithLayout().printValue(Utils.loadJsonObject());
    }

    /**
     * Delegates processing to appropriate printValue method based on type
     *
     * @param key       String key value
     * @param jsonValue JsonValue type
     */
    private void printValue(String key, JsonValue jsonValue) {
        if (key != null) printKey(key);

        if (jsonValue instanceof JsonString) {
            setSeparator();
            printValue((JsonString) jsonValue);
            return;
        }
        if (jsonValue instanceof JsonArray) {
            unsetSeparator();
            printValue((JsonArray) jsonValue);
            setSeparator();
            return;
        }
        if (jsonValue instanceof JsonObject) {
            System.out.println();
            setKeyIndent();
            unsetValueIndent();
            printValue((JsonObject) jsonValue);
            unsetKeyIndent();
            return;
        }
        if (jsonValue instanceof JsonNumber) {
            printValue((JsonNumber) jsonValue);
            return;
        }
        printValue(jsonValue.toString());
    }

    private void printValue(JsonArray jsonValues) {
        setValueIndent();
        System.out.println();
        jsonValues.forEach(jsonValue -> printValue(null, jsonValue));
        unsetValueIndent();
    }

    private void printValue(JsonObject jsonObject) {
        jsonObject.forEach(this::printValue);
    }

    private void printKey(String key) {
        System.out.print(keyIndent + key);
    }

    private void printValue(JsonString jsonString) {
        printValue(jsonString.getString());
    }

    private void printValue(JsonNumber jsonNumber) {
        printValue(jsonNumber.toString());
    }

    private void printValue(String value) {
        System.out.println(valueIndent + separator + value);
    }

    private void unsetValueIndent() {
        valueIndent = "";
    }

    private void setValueIndent() {
        valueIndent = "-----";
    }

    private void setKeyIndent() {
        keyIndent = "-----";
    }

    private void unsetKeyIndent() {
        keyIndent = "";
    }

    private void setSeparator() {
        separator = " : ";
    }

    private void unsetSeparator() {
        separator = "";
    }
}
