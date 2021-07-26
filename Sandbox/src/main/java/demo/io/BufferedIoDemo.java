package demo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedIoDemo {

    private static final int BUFFER_SIZE = 1024; // 実際にはもっと大きい値を
    private static final String INPUT_FILE = "files/flower.jpg";

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            readWrite(INPUT_FILE, "temp/flower1.jpg");
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken for reading and writing using default behaviour : "
                               + (endTime - startTime) + " milli seconds");

            startTime = System.currentTimeMillis();
            readWriteArrayBuffer(INPUT_FILE, "temp/flower2.jpg");
            endTime = System.currentTimeMillis();
            System.out.println("Time taken for reading and writing using custom buffering : "
                               + (endTime - startTime) + " milli seconds");

            startTime = System.currentTimeMillis();
            readWriteBufferedStreams(INPUT_FILE, "temp/flower3.jpg");
            endTime = System.currentTimeMillis();
            System.out.println("Time taken for reading and writing using buffered streams : "
                               + (endTime - startTime) + " milli seconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // every 1byte
    public static void readWrite(String fileFrom, String fileTo) throws IOException {
        try (InputStream in = new FileInputStream(fileFrom);
             OutputStream out = new FileOutputStream(fileTo)) {
            int bytedata;
            while ((bytedata = in.read()) >= 0) {
                out.write(bytedata);
            }
        }
    }

    public static void readWriteArrayBuffer(String fileFrom, String fileTo)
            throws IOException {
        try (InputStream in = new FileInputStream(fileFrom);
             OutputStream out = new FileOutputStream(fileTo)) {
            int length;
            byte[] bytes = new byte[BUFFER_SIZE];
            while ((length = in.read(bytes)) > 0) {
                out.write(bytes, 0, length); // length
            }
        }
    }

    public static void readWriteBufferedStreams(String fileFrom, String fileTo)
            throws IOException {
        BufferedInputStream inBuffer = null;
        BufferedOutputStream outBuffer = null;
        try {
            InputStream in = new FileInputStream(fileFrom);
            inBuffer = new BufferedInputStream(in, BUFFER_SIZE);

            OutputStream out = new FileOutputStream(fileTo);
            outBuffer = new BufferedOutputStream(out, BUFFER_SIZE);

            int bytedata;
            while ((bytedata = inBuffer.read()) >= 0) { // 呼び出し回数が多い。内部的にbufferを用いる
                outBuffer.write(bytedata);
            }
        } finally {
            if (inBuffer != null) inBuffer.close();
            if (outBuffer != null) outBuffer.close();
        }
    }

}
