package algorithms;

import java.util.Stack;

/**
 * ASIN Code Generator
 *
 * @author falcon
 */
public class AsinGenerator2 {
    public static final long LAST = 78_364_164_095L;

    /**
     * 基数変換（10 -> 36）
     *
     * @param num
     * @return
     */
    public static String convertToBase36(long num) {
        final int base = 36;
        final int len = 7;
        Stack<Integer> digits = new Stack<>();

        // 0 padding
        for (int i = 0; i < len; i++) {
            digits.push(num == 0 ? 0 : (int) (num % base));
            num /= base;
        }

        String str = "";
        while (!digits.empty()) {
            str += convertToChar(digits.peek());
            digits.pop();
        }

        return str;
    }

    /**
     * 0-35 -> 0-9A-Z
     *
     * @param num
     * @return
     */
    public static char convertToChar(int num) {
        if (num > 35 || num < 0) throw new NumberFormatException();

        return (char) (num + (num < 10 ? 48 : 55));
    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println(convertToBase36(12345678L));
        //System.out.println(convertToBase36(78_364_164_095L));
        //System.out.println(convertToBase36(78_364_164_096L));
        //System.out.println(convertToBase36(28_364_164_095L));
    }
}
