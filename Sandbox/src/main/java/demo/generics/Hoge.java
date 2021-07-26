package demo.generics;

public class Hoge {

    // Generic
    public static <A extends Number> A f(Number a) {
        A b = (A) a;
        return b;
    }

    // Non-Generic
    public static Number f2(Number a) {
        Number b = a;
        return b;
    }

    public static void main(String[] args) {
        int a = f(5.0);
        double b = f(5.0);

        int c = (int) f2(5.0);
        double d = (double) f2(5.0);
    }
}
