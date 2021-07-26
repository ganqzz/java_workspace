package demo.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ignore) {
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        };

        //ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        //ExecutorService executor = Executors.newCachedThreadPool(); // create thread on demand

        for (int i = 0; i < 10; i++) {
            executor.execute(task);
        }

        executor.shutdown(); // soft (graceful) shutdown
        System.out.println("after shutdown");

        //executor.execute(task); // not allowed after shutdown called

        // 全タスクが終了するまで待つパターン（綺麗に終わることを前提とする）
        do {
            try {
                executor.awaitTermination(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // ignore
            }
        } while (!executor.isTerminated());

        System.out.println("Finished all threads");
    }
}
