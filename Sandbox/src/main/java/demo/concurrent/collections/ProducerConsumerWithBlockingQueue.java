package demo.concurrent.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Producer-Consumer pattern, using BlockingQueue
 */
public class ProducerConsumerWithBlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

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
                    queue.put(start + count); // blocking
                    count++;
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
                    System.out.println(name + ": " + queue.take()); // blocking
                    count++;
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
