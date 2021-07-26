package algorithms.recursion;

import java.util.Scanner;

public class PowerFunction {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your base number and exponent:");
        double number = in.nextDouble();
        int exp = in.nextInt();
        System.out.println(power(number, exp));
    }

    private static double power(double x, int n) {
        if (n < 0) return power(1 / x, -n);
        if (n == 0) return 1.0;
        if (n == 1) return x;
        return x * power(x, n - 1);
    }
}
