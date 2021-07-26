package algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Fibonacci {

    private static Map<Integer, Long> memo = new HashMap<>();

    // For loop version (Dynamic Programming)
    public static long fibonacciLoop(int n) {
        if (n < 2) return n; // 0, 1

        long n1 = 0;
        long n2 = 1;
        for (int i = 2; i <= n; i++) {
            long tmp = n1 + n2;
            n1 = n2;
            n2 = tmp;
        }
        return n2;
    }

    // Bare Recursive (very slow)
    //public static long fib(int n) {
    //    return (n < 2) ? n : fib(n - 2) + fib(n - 1);
    //}

    // Memoized
    //public static long fib(int n) {
    //    Long result = memo.get(n);
    //    if (result != null) return result;
    //
    //    result = fib(n - 2) + fib(n - 1);
    //    memo.put(n, result);
    //    return result;
    //}

    // Memoized lambda (Java8)
    //public static long fib(int n) {
    //    return memo.computeIfAbsent(n, i -> fib(i - 2) + fib(i - 1));
    //}

    // Memoized lambda (Java8) 桁あふれ検知（n >= 93）
    public static long fib(int n) {
        return memo.computeIfAbsent(n, i -> Math.addExact(fib(i - 2), fib(i - 1)));
    }

    public static void main(String[] args) {
        memo.put(0, 0L);
        memo.put(1, 1L);
        IntStream.range(1, 101)
                 .forEach(i -> System.out.printf("%d: %d%n", i, fib(i)));
    }
}
