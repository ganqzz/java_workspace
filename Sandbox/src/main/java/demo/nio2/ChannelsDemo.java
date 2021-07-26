package demo.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class ChannelsDemo {

    public static void main(String[] args) {

        Path path = Paths.get("./", "temp/nio-channel.txt");

        try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(path,
                                                                            EnumSet.of(
                                                                                    StandardOpenOption.READ))) {
            ByteBuffer buffer = ByteBuffer.allocate(12);
            String encoding = System.getProperty("file.encoding");
            buffer.clear();

            while (seekableByteChannel.read(buffer) > 0) {
                buffer.flip();
                System.out.print(Charset.forName(encoding).decode(buffer));
                buffer.clear();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

        // Truncate

        try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(path,
                                                                            EnumSet.of(
                                                                                    StandardOpenOption.WRITE,
                                                                                    StandardOpenOption.TRUNCATE_EXISTING))) {

            ByteBuffer buffer = ByteBuffer.wrap("Sonnet XXXVI".getBytes());

            int write = seekableByteChannel.write(buffer);
            System.out.println("Number of written bytes: " + write);

            buffer.clear();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
