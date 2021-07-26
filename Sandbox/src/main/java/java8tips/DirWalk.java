package java8tips;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirWalk {

    public static void main(String[] args) {
        Path path = Paths.get(".");
        List<File> files = null;
        try (Stream<Path> stream = Files.walk(path, 2)) { // 2nd: Limit
            files = stream.map(Path::toFile)
                          .filter(File::isDirectory)
                          .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(files).forEach(System.out::println);
    }
}
