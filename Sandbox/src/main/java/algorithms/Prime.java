package algorithms;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Prime {
    public static void main(String[] args) {
        testPrimes(Prime::isPrime);
        testPrimes(Prime::isPrime2);
    }

    private static void testPrimes(IntPredicate p) {
        List<Integer> results = IntStream
                .rangeClosed(1, 100)
                .filter(p)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(results);
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
