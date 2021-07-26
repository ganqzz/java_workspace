package demo.concurrent.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService版タイマー
 * Runnable, Callableを使える点でTimerより優れる。
 */
public class ExecutorTimerDemo {

    public static void main(String[] args) {
        Runnable task = () -> System.out.println("action.");

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture1 = ses
                .scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ses.shutdown();

        System.out.println("---");

        ses = Executors.newSingleThreadScheduledExecutor(); // 再利用できないので再作成
        ScheduledFuture<?> scheduledFuture2 = ses
                .scheduleWithFixedDelay(task, 0, 500, TimeUnit.MILLISECONDS); // Runnableは再利用できる

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ses.shutdown();
    }
}
