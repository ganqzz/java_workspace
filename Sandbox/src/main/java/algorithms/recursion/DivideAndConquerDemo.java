package algorithms.recursion;

import java.util.stream.LongStream;

/**
 * Recursively sum a range of numbers
 * この例ではシングルスレッドで実行しているが、分割したものをパラレルに実行することでスループットを上げることが可能かもしれない
 * マージソートやクイックソートでも用いられる
 */
public class DivideAndConquerDemo {

    private static long recursiveSum(long lo, long hi) { // hi: inclusive
        if (hi - lo <= 100_000) { // base case threshold
            return LongStream.rangeClosed(lo, hi).sum(); // for loopより遅いかも
        } else {
            long mid = (hi + lo) / 2; // middle index for split
            long left = recursiveSum(lo, mid);
            long right = recursiveSum(mid + 1, hi);
            return left + right;
        }
    }

    public static void main(String[] args) {
        long total = recursiveSum(0, 1_000_000_000);
        System.out.println("Total sum is " + total);
    }
}
