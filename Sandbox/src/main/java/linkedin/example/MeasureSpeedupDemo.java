package linkedin.example;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * Measure the speedup of a parallel algorithm
 */
public class MeasureSpeedupDemo {

    // sequential implementation
    private static long sequentialSum(long lo, long hi) {
        long total = 0;
        for (long i = lo; i <= hi; i++)
            total += i;
        return total;
    }

    // sequential implementation
    private static long sequentialSum2(long lo, long hi) {
        return LongStream.rangeClosed(lo, hi).sum();
    }

    public static void main(String[] args) {
        final int NUM_EVAL_RUNS = 2;
        final long SUM_VALUE = 1_000_000_000L;

        System.out.println("Evaluating Sequential Implementation...");
        long sequentialResult = sequentialSum(0, SUM_VALUE); // "warm up"
        double sequentialTime = 0;
        for (int i = 0; i < NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            sequentialSum(0, SUM_VALUE);
            sequentialTime += System.currentTimeMillis() - start;
        }
        sequentialTime /= NUM_EVAL_RUNS;

        System.out.println("Evaluating Sequential Implementation 2...");
        long sequentialResult2 = sequentialSum2(0, SUM_VALUE); // "warm up"
        double sequentialTime2 = 0;
        for (int i = 0; i < NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            sequentialSum2(0, SUM_VALUE);
            sequentialTime2 += System.currentTimeMillis() - start;
        }
        sequentialTime2 /= NUM_EVAL_RUNS;

        System.out.println("Evaluating Parallel Implementation...");
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long parallelResult = pool.invoke(new RecursiveSum(0, SUM_VALUE)); // "warm up"
        pool.shutdown();
        double parallelTime = 0;
        for (int i = 0; i < NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            pool = ForkJoinPool.commonPool();
            pool.invoke(new RecursiveSum(0, SUM_VALUE));
            pool.shutdown();
            parallelTime += System.currentTimeMillis() - start;
        }
        parallelTime /= NUM_EVAL_RUNS;

        // display sequential and parallel results for comparison
        if (sequentialResult != parallelResult) {
            throw new AssertionError("ERROR: sequentialResult and parallelResult do not match!");
        }
        System.out.format("%d, Average Sequential Time: %.1f ms\n",
                          sequentialResult, sequentialTime);
        System.out.format("%d, Average Sequential2 Time: %.1f ms\n",
                          sequentialResult2, sequentialTime2);
        System.out.format("%d, Average Parallel Time: %.1f ms\n", parallelResult, parallelTime);
        System.out.format("Speedup: %.2f\n", sequentialTime / parallelTime);
        System.out.format("Efficiency: %.2f%%\n",
                          100 * (sequentialTime / parallelTime) /
                          Runtime.getRuntime().availableProcessors());
    }
}
