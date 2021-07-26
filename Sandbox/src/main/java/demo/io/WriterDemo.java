package demo.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class WriterDemo {
    private final static String FILE_NAME = "./temp/time.txt";

    public static void main(String[] args) throws IOException {
        //oldStyle();
        newStyle();
    }

    private static void oldStyle() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(FILE_NAME, true)); // create and append
            bw.write(new Date().toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void newStyle() {
        try (BufferedWriter bw = Files
                .newBufferedWriter(Paths.get(FILE_NAME), StandardCharsets.UTF_8,
                                   StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(new Date().toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
