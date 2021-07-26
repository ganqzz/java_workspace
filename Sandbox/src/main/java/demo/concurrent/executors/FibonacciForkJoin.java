package demo.concurrent.executors;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class FibonacciForkJoin {
    static final BigInteger TWO = BigInteger.valueOf(2);

    public static void main(String[] args) {
        HashMap<BigInteger, BigInteger> cache = new HashMap<>();
        cache.put(ZERO, ZERO);
        cache.put(ONE, ONE);

        @SuppressWarnings("serial")
        class Fibonacci extends RecursiveTask<BigInteger> {
            final BigInteger n;

            Fibonacci(BigInteger n) {
                this.n = n;
            }

            @Override
            protected BigInteger compute() {
                assert n.min(ZERO).equals(ZERO);
                return cache.computeIfAbsent(n, x -> {
                    Fibonacci f1 = new Fibonacci(n.subtract(ONE));
                    f1.fork();
                    return new Fibonacci(n.subtract(TWO)).compute().add(f1.join());
                });
            }
        }
        ForkJoinPool pool = new ForkJoinPool(3);
        log("version 2 start");
        for (int i = 500; i <= 505; i++) {
            log("fib(%d) = %d", i, pool.invoke(new Fibonacci(BigInteger.valueOf(i))));
        }
    }

    private static void log(String fmt, Object... args) {
        String now = String.format("%1$tT.%1$tL", System.currentTimeMillis());
        now = "--:" + now.substring(3);
        System.out.printf("%s [%s] %s%n", now, Thread.currentThread().getName(),
                          String.format(fmt, args));
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
