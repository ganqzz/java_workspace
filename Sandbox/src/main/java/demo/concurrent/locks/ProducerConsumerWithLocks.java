package demo.concurrent.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Producer-Consumer pattern, using Lock and Condition
 */
public class ProducerConsumerWithLocks {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> buffer = new ArrayList<>();

        Lock lock = new ReentrantLock();
        Condition isEmpty = lock.newCondition();
        Condition isFull = lock.newCondition();

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
                        lock.lock();

                        //int i = 10 / 0; // exception 対処できる

                        while (isFull(buffer)) {
                            // wait
                            isFull.await();
                        }

                        buffer.add(start + count);
                        count++;

                        // signal
                        isEmpty.signalAll();
                    } finally {
                        lock.unlock();
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
            public String call() throws InterruptedException, TimeoutException {
                int count = 0;
                while (count < 50) {
                    Thread.sleep(1);
                    try {
                        lock.lock();

                        //int i = 10 / 0; // exception うまく対処できない

                        while (isEmpty(buffer)) {
                            // wait
                            if (!isEmpty.await(500, TimeUnit.MILLISECONDS)) {
                                throw new TimeoutException("Consumer time out");
                            }
                        }

                        System.out.println(name + ": " + buffer.remove(0));
                        count++;

                        // signal
                        isFull.signalAll();
                    } finally {
                        lock.unlock();
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

    public static boolean isEmpty(List<Integer> buffer) {
        return buffer.size() == 0;
    }

    public static boolean isFull(List<Integer> buffer) {
        return buffer.size() == 10;
    }
}
