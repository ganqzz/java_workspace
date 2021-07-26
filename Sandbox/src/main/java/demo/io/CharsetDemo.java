package demo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Character code set
 * FileReader/FileWriterは使ってはいけない
 */
public class CharsetDemo {

    public static final String SRC = "./files/encode_test.txt";
    public static final String DEST = "./temp/encode_test.txt";

    public static void main(String[] args) {
        //demo();
        demo7();
    }

    private static void demo() {
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(SRC), "UTF-16LE"));
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DEST), "UTF-8"));
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
                out.write(c);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void demo7() {
        try (BufferedReader in = Files.newBufferedReader(Paths.get(SRC), StandardCharsets.UTF_16LE);
             BufferedWriter out = Files
                     .newBufferedWriter(Paths.get(DEST), StandardCharsets.UTF_8)) {
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
                out.write(c);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
