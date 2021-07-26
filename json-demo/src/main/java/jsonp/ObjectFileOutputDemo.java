package jsonp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonWriter;

/**
 * @version 1.0
 */
public class ObjectFileOutputDemo {

    public static void main(String... arg) throws IOException {

        File file = new File("json-output.json");
        System.out.println(file.getAbsolutePath());

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             JsonWriter jsonWriter = Json.createWriter(fileOutputStream)) {

            //if (!file.exists()) {
            //    file.createNewFile();
            //}

            //JsonWriter jsonWriter = Json.createWriter(fileOutputStream);
            jsonWriter.writeObject(Utils.buildJsonDocument());

            //jsonWriter.close();

            // Flush and close file output streams
            //fileOutputStream.flush();
            //fileOutputStream.close();
        }
    }
}
