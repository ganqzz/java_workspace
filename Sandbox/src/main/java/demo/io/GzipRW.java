package demo.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIPInputStream / GZIPOutputStream
 */
public class GzipRW {

    public static final String FILE = "temp/primitives.gz";

    public static void main(String[] args) {
        Path path = Paths.get(FILE);

        try (DataOutputStream out = new DataOutputStream(new GZIPOutputStream(
                Files.newOutputStream(path), true));) {
            out.writeInt(22);
            out.writeUTF("Fuga");
            for (int i = 0; i < 100; i++) {
                out.writeInt(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream in = new DataInputStream(new GZIPInputStream(
                Files.newInputStream(path)));) {
            System.out.println(in.readInt());
            System.out.println(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(Files.size(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
