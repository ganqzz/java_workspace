package demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 7以上では、SeekableByteChannelを実装
 */
public class FileCopybyChannel {

    public static void main(String[] args) throws IOException {
        copyFile(new File("files/flower.jpg"), new File("temp/flower_out.jpg"));
    }

    private static void copyFile(File in, File out) throws IOException {
        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        //inChannel.close();
        //outChannel.close();
        fis.close();
        fos.close();
    }
}
