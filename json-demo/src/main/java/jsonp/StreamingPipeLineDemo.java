package jsonp;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.json.stream.JsonParser;

import static javax.json.stream.JsonParser.Event.KEY_NAME;
import static javax.json.stream.JsonParser.Event.VALUE_NUMBER;
import static javax.json.stream.JsonParser.Event.VALUE_STRING;

/**
 * @version 1.0
 */
public class StreamingPipeLineDemo {

    private static PipedInputStream inputStream;
    private static PipedOutputStream outputStream;

    public static void main(String... args) throws IOException, InterruptedException {

        outputStream = new PipedOutputStream();
        inputStream = new PipedInputStream(outputStream);

        Thread thread1 = new Thread(() -> {
            try {
                outputStream.write("{".getBytes());
                while (true) {
                    outputStream.write("\"colour\":\"red\",".getBytes());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ignore) {
            }
        });

        Thread thread2 = new Thread(() -> {
            JsonParser parser = Json.createParser(inputStream);
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                System.out.println(event.toString());
                if (event == KEY_NAME || event == VALUE_STRING || event == VALUE_NUMBER) {
                    System.out.println(parser.getString());
                }
            }
            parser.close();
        });

        thread1.start();

        Thread.sleep(3000);

        thread2.start();
    }
}
