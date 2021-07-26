package demo.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ManageFiles {

    public static void main(String[] args) throws IOException {

        Path source = Paths.get("files/loremipsum.txt");
        System.out.println(source.getFileName());

        Path target = Paths.get("temp/newfile.txt");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        // Path toDelete = Paths.get("temp/todelete.txt");
//		Files.delete(toDelete);
//		System.out.println("File deleted");

        Path newdir = Paths.get("temp/newdir");
        Files.createDirectory(newdir);

        Files.move(source, newdir.resolve(source.getFileName()),
                   StandardCopyOption.REPLACE_EXISTING);

    }

}
