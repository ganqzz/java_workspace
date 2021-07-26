package java8tips;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesLines {

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get("files/hamlet.txt"))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
