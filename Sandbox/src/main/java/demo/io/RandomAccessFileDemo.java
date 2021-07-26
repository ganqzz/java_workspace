package demo.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * Read and Write
 */
public class RandomAccessFileDemo {

    public static void main(String[] args) {
        try {
            File src = new File("files/hamlet.txt");
            File dest = new File("temp/output.txt");

            copy(src, dest);

            RandomAccessFile randomFile = new RandomAccessFile(dest, "rw");
            randomFile.seek(45);
            randomFile.writeBytes("===TESTING===");
            randomFile.seek(100);
            randomFile.writeBytes("== Test Again ==");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copy(File src, File dest) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);

        int length;
        byte[] bytes = new byte[1024];
        while ((length = in.read(bytes)) > 0) {
            out.write(bytes, 0, length);
        }
        in.close();
        out.close();
    }
}
