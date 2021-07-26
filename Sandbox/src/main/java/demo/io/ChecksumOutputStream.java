package demo.io;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

/**
 * Custom io Demo
 */
public class ChecksumOutputStream extends FilterOutputStream {
    private Checksum cksum;

    public ChecksumOutputStream(OutputStream out, Checksum cksum) {
        super(out);
        this.cksum = cksum;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
        cksum.update(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(b, 0, b.length);
        cksum.update(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        cksum.update(b, off, len);
    }

    public Checksum getChecksum() {
        return cksum;
    }

    // main
    public static void main(String[] args) {
        Adler32 outChecker = new Adler32(); // faster CRC32
        CheckedOutputStream out = null;
        String content = "Hello VTC";
        try {
            out = new CheckedOutputStream(new FileOutputStream("temp/aCheckSumFile.dat"),
                                          outChecker);

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            out.write(contentInBytes);

            System.out.println("Done");
            System.out.println("Output stream check sum: " + outChecker.getValue());

            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
