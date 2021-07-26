package demo.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock: synchronous write and parallel read
 */
public class CacheWithReadWriteLock {

    private Map<Long, String> cache = new HashMap<>(); // non thread safe

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public String put(Long key, String value) {
        writeLock.lock();
        try {
            return cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(Long key) {
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {

        CacheWithReadWriteLock cache = new CacheWithReadWriteLock();

        class Producer implements Callable<String> {

            private Random rand = new Random();

            @Override
            public String call() throws Exception {
                while (true) {
                    long key = rand.nextLong();
                    cache.put(key, Long.toString(key));
                    Thread.sleep(1); // ウェイトを入れる
                    if (cache.get(key) == null) { // null: race condition
                        System.out.println(key + " is missing!");
                    }
                }
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        System.out.println("Adding value...");

        try {
            for (int i = 0; i < 4; i++) {
                executorService.submit(new Producer());
            }
        } finally {
            executorService.shutdown();
        }
    }
}
