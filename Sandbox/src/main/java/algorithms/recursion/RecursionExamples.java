package algorithms.recursion;

import java.util.Scanner;

public class RecursionExamples {

    private static Scanner in = new Scanner(System.in);
    private static int[] memo = new int[100];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // use recursion to print a list in reverse order
        int[] numList = {10, 20, 30, 40, 50};
        reversePrint(numList);
        System.out.println("");
        reversePrint2(numList);
        System.out.println("");

        System.out.println(factorial(10));
        System.out.println(factorialM(10));

        System.out.println(gcd(4, 6));
        System.out.println(gcd(20, 15));
    }

    private static void reversePrint(int[] numbers) {
        if (numbers.length == 0) return;

        System.out.print(" " + numbers[numbers.length - 1]);

        int[] arr = new int[numbers.length - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numbers[i];
        }

        reversePrint(arr);
    }

    // 別解
    private static void reversePrint2(int[] numbers) {
        if (numbers.length == 0) return;

        int[] arr = new int[numbers.length - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numbers[i + 1];
        }

        reversePrint2(arr);

        System.out.print(" " + numbers[0]);
    }

    private static int factorial(int b) {
        return b <= 1 ? 1 : b * factorial(b - 1);
    }

    // memoized
    private static int factorialM(int b) {
        if (b < 1) return 0;
        if (b == 1) return 1;

        memo[0] = 0;
        memo[1] = 1;

        if (memo[b] == 0) memo[b] = b * factorialM(b - 1);

        return memo[b];
    }

    /**
     * gcd Common divisor recursive function
     * based on Euclid
     */
    private static int gcd(int a, int b) {
        int temp = a;
        if (a < b) {
            a = b;
            b = temp;
        }

        return b == 0 ? a : gcd(b, a % b);
    }
}
