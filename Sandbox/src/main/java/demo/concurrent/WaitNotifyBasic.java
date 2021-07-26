package demo.concurrent;

/**
 * wait and notify
 */
public class WaitNotifyBasic {
    private static final Object mutex = new Object(); // mutex: mutual exclusion
    private static int count;

    private static class Worker extends Thread {

        @Override
        public void run() {
            synchronized (mutex) { // lock
                for (int i = 0; i < 10; i++) {
                    count++;
                    try {
                        sleep(200); // keep the monitor, so all the other threads are blocked
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(".");
                }
                System.out.println();

                mutex.notify(); // notify to the waiting thread
            } // unlock

            System.out.println(Thread.currentThread().getName() + ": END");
        }
    }

    public static void main(String[] args) {
        Thread single = new Worker();
        single.start();

        synchronized (mutex) { // lock
            try {
                System.out.println("Waiting for second thread to complete");
                // release the monitor, another thread can acquire it
                mutex.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // unlock

        try {
            single.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total count: " + count);
    }
}
