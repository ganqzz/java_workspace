package demo.exception;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchEx {
    private static int numerator, denominator, result;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("Enter the numerator: ");
                numerator = input.nextInt();
                System.out.print("Enter the denominator: ");
                denominator = input.nextInt();
                result = numerator / denominator;
                System.out.println("The result is " + result);
            } catch (InputMismatchException ie) {
                System.out.println("You tried to input non integer value. Try again.");
                input.next(); // バッファの消去
            } catch (ArithmeticException ae) {
                System.out.println("You tried to divide by zero. Try again.");
            } finally {
            }
        }
    }

    /**
     * 非チェック例外（RuntimeException）
     */
    public static void func() {
        throw new InputMismatchException();
    }

    /**
     * チェック例外
     */
    public static void func2() throws IOException {
        throw new IOException();
    }
}
