package demo.concurrent.locks;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch Demo
 */
public class CountDownLatchDemo {
    private static final int TASKS_NUM = 4;

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(TASKS_NUM);

        Runnable task = () -> {
            try {
                printInfo("doing work...");
                Thread.sleep((long) (Math.random() * 1000 + 200));

                latch.countDown(); // reduce count

                printInfo("done");
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        };

        // create and start threads
        for (int i = 1; i <= TASKS_NUM; ++i) {
            new Thread(task, "Worker" + i).start();
        }

        try {
            printInfo("waiting for all workers done");

            //latch.await(); // wait for all worker threads to finish
            boolean await = latch.await(1000, TimeUnit.MILLISECONDS); // with timeout

            // do something after the latch is reached to 0

            printInfo("all workers done: " + await + ": " + latch.getCount());
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    private static void printInfo(String s) {
        System.out.println(new Date(System.currentTimeMillis())
                           + " [" + Thread.currentThread().getName()
                           + "] " + s);
    }
}
