package demo.concurrent.executors;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Executor Demo
 */
public class ExecutorDemo2 {

    private static class Worker implements Runnable {
        private final String message;

        // Constructor to assign a message when creating a new thread
        private Worker(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +
                               ": [Start] message = " + message);

            // call workToBeDone method to simulate a delay
            workToBeDone();

            System.out.println(Thread.currentThread().getName() + ": [End]");
        }

        private void workToBeDone() {
            // something to do
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignore) {
            }
        }
    }

    // main()
    public static void main(String[] args) {
        // creating a pool of 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Runnable task = new Worker("I'm thread " + i);
            // calling execute method of ExecutorService
            executor.execute(task);
        }

        executor.shutdown();
        System.out.println("after shutdown");

        // TimeOutで強制終了するパターン
        try {
            if (!executor.awaitTermination(400, TimeUnit.MILLISECONDS)) {
                List<Runnable> notStartedTasks = executor.shutdownNow(); // hard shutdown
                System.out.println("Not started tasks count = " + notStartedTasks.size());
                System.exit(777);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            executor.shutdownNow();
            System.exit(999);
        }

        System.out.println("Finished all threads");
    }
}
