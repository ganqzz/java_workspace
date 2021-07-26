package demo.concurrent;

/**
 * lock is reentrant
 */
public class SynchronizedReentrantDemo {
    private static final Object mutex = new Object(); // mutex: mutual exclusion
    private static int count; // shared object

    private static class Worker extends Thread {

        @Override
        public void run() {
            synchronized (mutex) { // lock
                for (int i = 0; i < 10; i++) {
                    count++;
                    synchronized (mutex) { // second lock to the same lock object
                        try {
                            sleep(200); // keep the monitor, so all the other threads are blocked
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ": count: " + count);
                    } // unlock the second lock
                }
            } // unlock

            System.out.println(Thread.currentThread().getName() + ": END");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread w1 = new Worker();
        Thread w2 = new Worker();
        w1.start();
        w2.start();

        w1.join();
        w2.join();

        System.out.println("Total count: " + count);
    }
}
