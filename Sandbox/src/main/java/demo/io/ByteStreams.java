package demo.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteStreams {

    private static final String INPUT_FILE = "files/flower.jpg";

    public static void main(String[] args) {
        directReadWrite();
        readToMemory();
    }

    private static void directReadWrite() {
        try (FileInputStream in = new FileInputStream(INPUT_FILE);
             FileOutputStream out = new FileOutputStream("temp/newflower.jpg");) {
            //int c;
            //while ((c = in.read()) != -1) {
            //    out.write(c);
            //}
            int length;
            byte[] buffer = new byte[1024];
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length); // 一番最後が1024ではない可能性があるため
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readToMemory() {
        try (FileInputStream in = new FileInputStream(INPUT_FILE);) {
            List<byte[]> image = new ArrayList<>();
            int length;
            byte[] buffer = new byte[1024];
            while ((length = in.read(buffer)) > 0) {
                // bufferを使い回すので、copyする点を忘れないこと！
                image.add(Arrays.copyOf(buffer, length)); // 一番最後が1024ではない可能性があるため
            }
            System.out.println(image.get(0).length);
            System.out.println(image.get(image.size() - 2).length);
            System.out.println(image.get(image.size() - 1).length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
