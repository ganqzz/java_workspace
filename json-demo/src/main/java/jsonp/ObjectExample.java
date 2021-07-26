package jsonp;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;

/**
 * @version 1.0
 */
public class ObjectExample {

    //private String jsonFileArray = Utils.ROOT + System.getProperty("file.separator") + "jsondata-array.json";
    //private String jsonFileObject = Utils.ROOT + System.getProperty("file.separator") + "jsondata-object.json";
    private static final String jsonFileArray = "/jsondata-array.json";
    private static final String jsonFileObject = "/jsondata-object.json";

    /**
     * Builds a JsonObject from a Stirng of JSON data.
     *
     * @return a JsonObject built from a String of JSON data
     */
    public JsonObject loadJsonString() {

        JsonReader jsonReader = Json.createReader(new StringReader(Utils.JSON));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        return jsonObject;
    }

    /**
     * Builds a JsonObject from a flat-file source containing Json data.
     *
     * @return a JsonObject built from the flat-file
     * @throws FileNotFoundException
     */
    public JsonObject loadJsonObject() throws FileNotFoundException {

        //JsonReader reader = Json.createReader(new FileReader(jsonFileObject));
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(jsonFileObject));
        JsonObject jsonObject = reader.readObject();

        return jsonObject;
    }

    /**
     * Builds a JsonStructute from a flat-file source containing Json Array data.
     *
     * @return a JsonStructure built from the flat-file
     * @throws FileNotFoundException
     */
    public JsonStructure loadJsonStructure() throws FileNotFoundException {

        //JsonReader reader = Json.createReader(new FileReader(jsonFileArray));
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(jsonFileArray));
        JsonStructure jsonStructure = reader.read();

        return jsonStructure;
    }

    public JsonObject buildJsonDocument() {
        return Utils.buildJsonDocument();
    }

    /**
     * Writer demo
     */
    public String writeJsonStructure() {

        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stringWriter);
        jsonWriter.writeObject(Utils.buildJsonDocument());
        jsonWriter.close();

        return stringWriter.toString();
    }
}
