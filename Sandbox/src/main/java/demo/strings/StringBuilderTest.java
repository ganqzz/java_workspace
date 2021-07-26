package demo.strings;

import java.io.IOException;

/*
 * StringBufferは、スレッドセーフなのでオーバーヘッドがある。
 */
public class StringBuilderTest {
    private static final int ITERATIONS = 1000;
    private static final int BUFFSIZE = 16;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            concatStrings(new StringBuffer());
            concatStrings(new StringBuilder());
            // sConcat(); // 桁違いに遅い。
            System.out.println("---");
        }
    }

    private static void concatStrings(Appendable a) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < ITERATIONS; i++) {
            for (int c = 0; c < 10000; c++) {
                try {
                    a.append((char) c);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long end = System.currentTimeMillis();

        System.out.println(a.getClass().getSimpleName() + ":\t" + (end - start));
    }

    private static void sConcat() {
        String s = "";

        long start = System.currentTimeMillis();

        for (int i = 0; i < ITERATIONS; i++) {
            for (int c = 0; c < 10000; c++) {
                s += (char) c;
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("String Concatination:\t" + (end - start));
    }
}
