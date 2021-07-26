package demo.concurrent;

public class ThreadLifecycleDemo {

    private static class Worker extends Thread {
        private boolean sleeping = true;
        private boolean waiting = true;

        @Override
        public void run() {
            System.out.println("  " + getName() + ": started"); // #1

            while (sleeping) {
                System.out.println("  " + getName() + ": sleeping...");
                try {
                    Thread.sleep(200); // #2
                } catch (InterruptedException e) {
                }
            }

            System.out.println("  " + getName() + ": waked up");

            synchronized (this) {
                try {
                    while (waiting) {
                        System.out.println("  " + getName() + ": waiting...");
                        wait(); // #3
                    }
                } catch (InterruptedException e) {
                    System.out.println("  " + getName() + ": wait() interrupted");
                }
            }

            System.out.println("  " + getName() + ": go for work");

            try {
                System.out.println("  " + getName() + ": sleeping for a moment");
                Thread.sleep(1000); // #4
            } catch (InterruptedException e) {
            }

            System.out.println("  " + getName() + ": terminating");
        }

        private synchronized void wakeup() {
            waiting = false;
            notify();
        }
    }

    private static void printThreadStatus(Thread thread) {
        System.out.println(thread.getName() + ": isAlive=" +
                           thread.isAlive() + ", State=" +
                           thread.getState());
    }

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        printThreadStatus(worker); // #0

        System.out.println("main -> worker starting");
        worker.start();
        printThreadStatus(worker); // #1

        Thread.sleep(500);
        printThreadStatus(worker); // #2

        System.out.println("main -> worker stop sleeping");
        worker.sleeping = false;

        Thread.sleep(500);
        printThreadStatus(worker); // #3

        System.out.println("main -> worker stop waiting");
        //worker.wakeup(); // flagで抜ける
        worker.interrupt(); // interruptで抜ける

        Thread.sleep(500);
        printThreadStatus(worker); // #4

        System.out.println("main waiting for worker terminated");
        worker.join();

        printThreadStatus(worker);
    }
}
