package demo.concurrent.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * Producer-Consumer pattern, using Semaphore
 */
public class ProducerConsumerWithSemaphores {
    private static final int SIZE = 3;

    public static void main(String[] args) throws InterruptedException {
        List<Integer> buffer = new ArrayList<>();

        Semaphore useQueue = new Semaphore(1); // binary
        Semaphore fullCount = new Semaphore(0);
        Semaphore emptyCount = new Semaphore(SIZE);

        class Producer implements Callable<String> {
            String name;
            int start;

            Producer(int i) {
                this.name = "Producer" + i;
                start = 100 * i;
            }

            @Override
            public String call() throws InterruptedException {
                int count = 0;
                while (count < 50) {
                    Thread.sleep(1);
                    try {
                        emptyCount.acquire();
                        useQueue.acquire();

                        buffer.add(start + count);
                        count++;

                        //int i = 10 / 0; // exception うまく対処できない

                        useQueue.release();
                        fullCount.release();
                    } catch (Exception e) {
                        useQueue.release();
                        emptyCount.release();
                    }
                }
                return name + ": Produced " + count;
            }
        }

        class Consumer implements Callable<String> {
            String name;

            Consumer(int i) {
                this.name = "Consumer" + i;
            }

            @Override
            public String call() throws InterruptedException {
                int count = 0;
                while (count < 50) {
                    Thread.sleep(1);
                    try {
                        fullCount.acquire();
                        useQueue.acquire();

                        System.out.println(name + ": " + buffer.remove(0));
                        count++;

                        //int i = 10 / 0; // exception うまく対処できない

                        useQueue.release();
                        emptyCount.release();
                    } catch (Exception e) {
                        useQueue.release();
                        fullCount.release();
                    }
                }
                return name + ": Consumed " + count;
            }
        }

        List<Callable<String>> producersAndConsumers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            producersAndConsumers.add(new Producer(i));
        }
        for (int i = 0; i < 2; i++) {
            producersAndConsumers.add(new Consumer(i));
        }

        System.out.println("--- Producers and Consumers launched");

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);
            System.out.println("--- Producers and Consumers ended");

            futures.forEach(
                    future -> {
                        try {
                            System.out.println(future.get());
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println("Exception: " + e.getMessage());
                        }
                    });
        } finally {
            executorService.shutdown();
            System.out.println("--- Executor service shut down");
        }
    }
}
