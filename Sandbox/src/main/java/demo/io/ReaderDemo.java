package demo.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderDemo {

    private final static String FILE_NAME = "./files/alphabet.txt";

    public static void main(String[] args) throws IOException {
        Reader br = new BufferedReader(new FileReader(FILE_NAME));
        System.out.println("markSupported: " + br.markSupported());
        br.mark(10);
        System.out.println((char) br.read());
        br.skip(5);
        System.out.println((char) br.read());
        br.reset();
        System.out.println((char) br.read());
        br.close();
    }
}
