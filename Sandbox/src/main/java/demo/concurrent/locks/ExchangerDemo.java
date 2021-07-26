package demo.concurrent.locks;

import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        String[] sample1 = {"AB", "CDE", "FG"};
        String[] sample2 = {"123", "456", "7890"};
        for (int i = 0; i < sample1.length; i++) {
            final String s1 = sample1[i];
            Future<String> result1 = threadPool.submit(new Callable<String>() {
                String name = "task1";

                @Override
                public String call() throws Exception {
                    log("%s: value = [%s]", name, s1);
                    sleep(100L); // 何か処理
                    String exchanged = exchanger.exchange(s1); // throws InterruptedException
                    log("%s: exchanged = [%s]", name, exchanged);
                    return exchanged;
                }
            });

            final String s2 = sample2[i];
            Future<String> result2 = threadPool.submit(new Callable<String>() {
                String name = "task2";

                @Override
                public String call() throws Exception {
                    log("%s: value = [%s]", name, s2);
                    sleep(500L); // 何か処理 task1より時間が掛かる
                    String exchanged = exchanger.exchange(s2); // throws InterruptedException
                    log("%s: exchanged = [%s]", name, exchanged);
                    return exchanged;
                }
            });

            try {
                log("task1結果=%s", result1.get());
                log("task2結果=%s", result2.get());
            } catch (InterruptedException | ExecutionException e) {
                log("task実行中に例外が発生しました: 例外=%s", e);
            }
        }
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
