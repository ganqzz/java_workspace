package demo.concurrent.problems;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Lock mess
 */
public class LockMessDemo extends Thread {

    /**
     * ロック対象は、Worker　Thread自身。
     */
    private static class WorkerWrong extends Thread {

        private volatile boolean quittingTime = false;

        @Override
        public void run() {
            while (!quittingTime) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {}

                System.out.println("Still working...");
            }

            System.out.println("Coffee is good!");
        }

        synchronized void quit() throws InterruptedException {
            quittingTime = true;
            System.out.println("Calling join");
            // 一旦ロックを手放す（wait）。意図せずkeepWorking()が呼ばれてしまう。
            // Thread.join(ms)参照
            join();
            System.out.println("Back from join");
        }

        synchronized void keepWorking() {
            quittingTime = false;
            System.out.println("Keep working");
        }
    }

    /**
     * ロックオブジェクトを別にする
     */
    private static class Worker extends Thread {

        private final Object lock = new Object(); // ロックオブジェクト（Mutex）
        private volatile boolean quittingTime = false;

        @Override
        public void run() {
            while (!quittingTime) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {}

                System.out.println("Still working...");
            }

            System.out.println("Coffee is good!");
        }

        void quit() throws InterruptedException {
            synchronized (lock) {
                quittingTime = true;
                System.out.println("Calling join");
                join(); // Worker Threadのロック解除は関係ない
                System.out.println("Back from join");
            }
        }

        void keepWorking() {
            synchronized (lock) {
                quittingTime = false;
                System.out.println("Keep working");
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        final WorkerWrong worker = new WorkerWrong();
        //final Worker worker = new Worker();

        worker.start();

        long begin = System.currentTimeMillis();

        Timer t = new Timer(true); // Daemon thread
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                worker.keepWorking();
            }
        }, 500);

        Thread.sleep(400);
        worker.quit();

        System.out.println("END: " + (System.currentTimeMillis() - begin));
    }
}
