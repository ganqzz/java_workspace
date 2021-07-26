package demo.concurrent;

/**
 * Mutual Exclusion by synchronized block (intrinsic lock)
 */
public class SynchronizedBasic {
    private static final Object mutex = new Object(); // mutex: mutual exclusion
    private static int count; // shared object

    public static void main(String[] args) {
        Runnable giantLockTask = () -> {
            synchronized (mutex) { // lock
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100); // keep the monitor, so all the other threads are blocked
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + ": count: " + count);
                }
            } // unlock

            System.out.println(Thread.currentThread().getName() + ": END");
        };

        demo(giantLockTask);

        System.out.println("---");
        count = 0;

        Runnable minimizedLockTask = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mutex) { // lock
                    count++; // read and write (and put)
                    System.out.println(Thread.currentThread().getName() + ": count: " + count);
                } // unlock
            }

            System.out.println(Thread.currentThread().getName() + ": END");
        };

        demo(minimizedLockTask);
    }

    private static void demo(Runnable task) {
        Thread[] workers = new Thread[4];

        for (int i = 0; i < 4; i++) {
            workers[i] = new Thread(task, "worker" + i);
            workers[i].start();
        }

        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total count: " + count);
    }
}
