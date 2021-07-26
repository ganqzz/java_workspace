package demo.concurrent.atomics.atomiccounter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NonAtomicCounter {
    private static final int THREADS = 100;

    private static int counter = 0;

    public static void main(String[] args) {

        class Incrementer implements Runnable {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try { // ばらけさせるためのウェイトを入れる
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter++;
                }
            }
        }

        class Decrementer implements Runnable {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try { // ばらけさせるためのウェイトを入れる
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter--;
                }
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS * 2);
        List<Future<?>> futures = new ArrayList<>();

        try {
            for (int i = 0; i < THREADS; i++) {
                futures.add(executorService.submit(new Incrementer()));
            }
            for (int i = 0; i < THREADS; i++) {
                futures.add(executorService.submit(new Decrementer()));
            }

            futures.forEach(
                    future -> {
                        try {
                            future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println(e.getMessage());
                        }
                    }
            );

            System.out.println("counter = " + counter);

        } finally {
            executorService.shutdown();
        }
    }
}
