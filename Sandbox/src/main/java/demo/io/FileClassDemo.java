package demo.io;

import java.io.File;
import java.io.IOException;
import java.util.Date;

class FileClassDemo {
    public static void main(String[] args) throws IOException {
        // 定数を表示する
        System.out.println("pathSeparatorChar = " + File.pathSeparatorChar);
        System.out.println("separatorChar = " + File.separatorChar);

        // いくつかのメソッドをテストする
        File file = new File("files/loremipsum.txt");
        System.out.println("getName() = " + file.getName());
        System.out.println("getParent() = " + file.getParent());
        System.out.println("getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("getCanonicalPath() = " + file.getCanonicalPath()); // raise IOException
        System.out.println("getPath() = " + file.getPath());
        System.out.println("exists() = " + file.exists());
        System.out.println("isDirectory() = " + file.isDirectory());
        System.out.println("isFile() = " + file.isFile());
        System.out.println("isHidden() = " + file.isHidden());
        System.out.println("canRead() = " + file.canRead());
        System.out.println("canWrite() = " + file.canWrite());
        System.out.println("canExecute() = " + file.canExecute());
        System.out.println("Last modified = " + new Date(file.lastModified()));
        System.out.println("length() = " + file.length());
    }
}
