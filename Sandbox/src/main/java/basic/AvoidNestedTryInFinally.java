package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AvoidNestedTryInFinally {
    public static void main(String[] args) {
        AvoidNestedTryInFinally app = new AvoidNestedTryInFinally();
        try {
            app.exec();
        } catch (IOException e) {
            System.err.println("in.close()でIOExceptionが発生");
        }
    }

    public void exec() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            throw new IOException();
        } catch (IOException e) {
            System.err.println("in.readLine()でIOExceptionが発生");
            throw new IOException();
        } finally {
            in.close(); // try-catchで囲わない。
        }
    }
}
