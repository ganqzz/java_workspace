package java8tips;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Example of UncheckedIOException
 */
public class UncheckedIOExceptionDemo {
    public static void main(String[] args) {
        processData("hoge hoge");
    }

    private static void processData(String s) {
        try {
            // 何かの処理でIOExceptionが出ると仮定
            throw new IOException();
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);  // RuntimeExceptionとしてラップ
        }
    }
}
