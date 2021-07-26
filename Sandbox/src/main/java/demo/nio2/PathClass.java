package demo.nio2;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathClass {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("files/loremipsum.txt");

        System.out.println(path.toString()); // OS dependent path ("\" in Windows)
        System.out.println(path.getFileName());
        System.out.println(path.getNameCount());
        System.out.println(path.getName(path.getNameCount() - 1));

        Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
        System.out.println(realPath);

        System.out.println(Paths.get("root").resolve(Paths.get("hoge")));
        System.out.println(Paths.get("root").resolve(Paths.get("/hoge")));
        System.out.println(Paths.get("files/data1.txt").resolve(Paths.get("data2.txt")));
        System.out.println(Paths.get("/src/java").relativize(Paths.get("/src/java/org/hoge")));
        System.out.println(Paths.get("src/java").relativize(Paths.get("src/java/org/hoge")));
        System.out
                .println(Paths.get("src/java").relativize(Paths.get("project/src/java/org/hoge")));
    }
}
