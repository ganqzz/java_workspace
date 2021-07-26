package demo.concurrent.locks;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * CyclicBarrier Demo
 */
public class CyclicBarrierDemo {

    private static final int THREAD_COUNT = 10;
    private static final int INTEGERS = 1000;
    private static final int CHUNK_SIZE = INTEGERS / THREAD_COUNT;
    private static List<Integer>[] results = new List[THREAD_COUNT];

    public static void main(String[] args) {

        // barrierAction
        Runnable actionTask = () -> {
            List<Integer> finalResult = Stream.of(results)
                                              .flatMap(Collection::stream)
                                              .collect(Collectors.toList());
            printInfo("in barrierAction task: " + finalResult.toString());
            printInfo("all workers done");
        };

        final CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT, actionTask);

        class Task implements Runnable {
            private int index;
            private List<Integer> inputs;

            Task(int index) {
                this.index = index;
                this.inputs = createInputs(index);
            }

            @Override
            public void run() {
                try {
                    printInfo("doing work...");

                    results[index] = inputs.stream()
                                           .filter(CyclicBarrierDemo::isPrime)
                                           .collect(Collectors.toList());

                    barrier.await(); // wait for all other threads to finish

                    // do something after the randevu point

                    printInfo("done");
                } catch (InterruptedException | BrokenBarrierException e) {
                    System.err.println(e);
                }
            }
        }

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

        // create and start threads
        for (int i = 0; i < THREAD_COUNT; ++i) {
            pool.submit(new Task(i));
        }

        printInfo("waiting for all workers done");

        pool.shutdown();

        do {
            try {
                pool.awaitTermination(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // ignore
            }
        } while (!pool.isTerminated());

        printInfo("all workers done");
    }

    private static List<Integer> createInputs(int index) {
        int start = index * CHUNK_SIZE;
        return IntStream.range(start, start + CHUNK_SIZE)
                        .boxed()
                        .collect(Collectors.toList());
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static void printInfo(String s) {
        System.out.println(new Date(System.currentTimeMillis())
                           + " [" + Thread.currentThread().getName()
                           + "] " + s);
    }
}
