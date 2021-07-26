package demo.nio2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManipulation7 {
    public static void main(String[] args) {

        // since 1.7 -- java.nio.file
        Path file = Paths.get("./files/hamlet.txt");
        Path dir = Paths.get(".");

        System.out.println(file.toString());
        System.out.println(file.getFileName());
        System.out.println(file.getNameCount());
        System.out.println(file.getName(file.getNameCount() - 1));
        System.out.println(dir.toString());

        // Creating a File
        try {
            Files.createFile(file);
            ls(dir, "*.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Moving a File
        try {
            Files.move(file, Paths.get("./movedfile.txt"));
            ls(dir, "*.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deleting a File
        try {
            Files.deleteIfExists(Paths.get("./movedfile.txt"));
            ls(dir, "*.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ls(Path dir, String glob) throws IOException {
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir, glob);
        System.out.println();
        for (Path path : stream) {
            System.out.println(path.toString());
        }
    }
}
