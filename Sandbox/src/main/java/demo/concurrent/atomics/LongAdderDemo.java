package demo.concurrent.atomics;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LongAdderDemo {

    public static void main(String[] args) {
        int nThread = 1_000;
        LongAdder longAdder = new LongAdder();
        AtomicLong atomicLong = new AtomicLong();
        long t;
        int sum;

        log("開始(AtomicLong)");
        t = System.currentTimeMillis();
        sum = IntStream.range(0, nThread)
                       .parallel()
                       .map(i -> {
                           IntStream.range(0, 200_000).forEach(j -> atomicLong.incrementAndGet());
                           return 1;
                       })
                       .sum();
        log("終了(AtomicLong): 所要時間＝%dミリ秒,", System.currentTimeMillis() - t);
        log("        終了スレッド数＝%d, カウント=%s", sum, atomicLong);

        log("開始(LongAdder)");
        t = System.currentTimeMillis();
        sum = IntStream.range(0, nThread)
                       .parallel()
                       .map(i -> {
                           IntStream.range(0, 200_000).forEach(j -> longAdder.increment());
                           return 1;
                       })
                       .sum();
        log("終了(LongAdder): 所要時間＝%dミリ秒,", System.currentTimeMillis() - t);
        log("        終了スレッド数＝%d, カウント=%s", sum, longAdder);
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
