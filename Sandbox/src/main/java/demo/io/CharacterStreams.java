package demo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CharacterStreams {

    private static final String SOURCE = "files/loremipsum.txt";
    private static final String TARGET = "temp/newfile.txt";

    public static void main(String[] args) {
        demo7();
    }

    private static void demo7() {
        Path source = Paths.get(SOURCE);
        Path target = Paths.get(TARGET);

        //Charset charset = Charset.forName("US-ASCII");
        Charset charset = StandardCharsets.US_ASCII;

        // sourceとtargetが別ファイルであれば、同時に開いても問題ない
        try (BufferedReader reader = Files.newBufferedReader(source, charset);
             BufferedWriter writer = Files.newBufferedWriter(target, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                writer.write(line.toUpperCase());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
