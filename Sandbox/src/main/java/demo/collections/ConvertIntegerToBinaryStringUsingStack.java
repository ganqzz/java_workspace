package demo.collections;

import java.util.Scanner;
import java.util.Stack;

public class ConvertIntegerToBinaryStringUsingStack {

    static String binary(int number) {
        final int base = 2;  // 2進数
        Stack<Integer> digits = new Stack<>();

        do {
            digits.push(number % base);
            number /= base;
        } while (number != 0);

        String bits = "";
        while (!digits.empty()) {
            bits += digits.peek();
            digits.pop();
        }
        return bits;
    }

    public static void main(String[] args) {
        System.out.print("Enter a number to convert for: ");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        System.out.println(num + " in binary is " + binary(num));
    }
}
