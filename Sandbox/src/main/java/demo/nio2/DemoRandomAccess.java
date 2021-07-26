package demo.nio2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DemoRandomAccess {

    public static void main(String[] args) {
        Path startPath = Paths.get("Outputfile");
        Path hamletPath = Paths.get("Hamlet");
        try {
            Files.delete(hamletPath);
            Files.copy(startPath, hamletPath);

            File hamlet = hamletPath.toFile();

            RandomAccessFile randomFile = new RandomAccessFile(hamlet, "rw");
            randomFile.seek(45);
            randomFile.writeBytes("===TESTING===");
            randomFile.seek(100);
            randomFile.writeBytes("== Test Again ==");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
