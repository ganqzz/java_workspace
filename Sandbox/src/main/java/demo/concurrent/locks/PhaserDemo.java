package demo.concurrent.locks;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.stream.Stream;

public class PhaserDemo {

    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Random r = new Random(System.currentTimeMillis());

        log("開始");
        Stream.of("A", "B", "C").forEach(x -> {
            phaser.register();
            threadPool.execute(() -> {
                try {
                    Stream.of(1, 2).forEach(i -> {
                        String prefix = String.format("%s: [フェイズ #%d]", x, i);
                        long millis = (r.nextInt(10) * 200) + 500L;
                        log(prefix + "の処理中 (sleep %d millis)", millis);
                        sleep(millis);
                        log(prefix + "の完了を待機");
                        int phaseNumber = phaser.arriveAndAwaitAdvance();
                        log("到着したフェイズ番号 = " + phaseNumber);
                    });
                } finally {
                    phaser.arriveAndDeregister();
                }
            });
        });

        sleep(3_000L);
        log("フェイズに途中参加");
        phaser.register();

        sleep(5_000L);
        log("途中参加したフェイズの開始を待機");
        phaser.arriveAndAwaitAdvance();

        log("終了");
        threadPool.shutdown();
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
