package demo.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TempFileDemo {
    public static void main(String[] args) {
        try {
            // create a temp file
            File temp = File.createTempFile("tempfiletest", ".tmp");
            //temp.deleteOnExit(); // <- ココ

            String path = temp.getAbsolutePath();
            System.err.println("Temp file created: " + path);

            // open a handle to it
            RandomAccessFile fh = new RandomAccessFile(temp, "rw");
            System.err.println("Temp file opened for random access.");

            // try to delete the file immediately
            boolean deleted = false;
            try {
                deleted = temp.delete();
            } catch (SecurityException e) {
                // ignore
            }

            // else delete the file when the program ends
            if (deleted) {
                System.err.println("Temp file deleted.");
            } else {
                // Windowsオープンファイル削除不可
                // closeしていないファイルは削除されないことに注意！
                // デモのためここで宣言しているが、実際はファイル作成時に宣言するのがいいだろう。
                temp.deleteOnExit();
                System.err.println("Temp file scheduled for deletion.");
            }

            try {
                // test writing data to the file
                String str = "A quick brown fox jumps over the lazy dog.";
                fh.writeUTF(str);
                System.err.println("Wrote: " + str);

                // test reading the data back from the file
                fh.seek(0);
                String out = fh.readUTF();
                System.err.println("Read: " + out);

            } finally {
                // close the file
                fh.close();
                System.err.println("Temp file closed.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
