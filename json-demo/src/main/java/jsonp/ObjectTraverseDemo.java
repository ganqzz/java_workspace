package jsonp;

import java.io.FileNotFoundException;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 * Traverse demo
 *
 * @version 1.0
 */
public class ObjectTraverseDemo {

    public static void main(String... args) throws FileNotFoundException {
        navigateJsonStructure(Utils.loadJsonObject(), null);
    }

    private static void navigateJsonStructure(JsonValue jsonValue, String key) {
        if (key != null) printKey(key);

        switch (jsonValue.getValueType()) {
        case OBJECT:
            JsonObject jsonObject = (JsonObject) jsonValue;
            for (String name : jsonObject.keySet())
                navigateJsonStructure(jsonObject.get(name), name);
            break;
        case ARRAY:
            for (JsonValue value : (JsonArray) jsonValue)
                navigateJsonStructure(value, null);
            break;
        case STRING:
            printValue(((JsonString) jsonValue).getString());
            break;
        case NUMBER:
            printValue(jsonValue.toString());
            break;
        case TRUE:
        case FALSE:
        case NULL:
            printValue(jsonValue.getValueType().toString());
            break;
        }
    }

    private static void printKey(String key) {
        System.out.print(key + ": ");
    }

    private static void printValue(String x) {
        System.out.println(x);
    }
}
