package demo.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockComparison {

    private static final long SLEEP_TIME = 10L;

    private interface Value<T> {
        T read();

        void write(T v);
    }

    private static class SynchronizedValue<T> implements Value<T> {
        T o;

        @Override
        public T read() {
            synchronized (this) {
                sleep(SLEEP_TIME);
                return this.o;
            }
        }

        @Override
        public void write(T o) {
            synchronized (this) {
                sleep(SLEEP_TIME);
                this.o = o;
            }
        }
    }

    private static class LockValue<T> implements Value<T> {
        T o;
        Lock lock = new ReentrantLock();

        @Override
        public T read() {
            lock.lock();
            try {
                sleep(SLEEP_TIME);
                return this.o;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void write(T o) {
            lock.lock();
            try {
                sleep(SLEEP_TIME);
                this.o = o;
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ReadWriteLockValue<T> implements Value<T> {
        T o;
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock rlock = lock.readLock();
        Lock wlock = lock.writeLock();

        @Override
        public T read() {
            rlock.lock();
            try {
                sleep(SLEEP_TIME);
                return this.o;
            } finally {
                rlock.unlock();
            }
        }

        @Override
        public void write(T o) {
            wlock.lock();
            try {
                sleep(SLEEP_TIME);
                this.o = o;
            } finally {
                wlock.unlock();
            }
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        benchmark(new SynchronizedValue<Integer>(), 5, 5);
        benchmark(new LockValue<Integer>(), 5, 5);
        benchmark(new ReadWriteLockValue<Integer>(), 5, 5);

        benchmark(new SynchronizedValue<Integer>(), 20, 5);
        benchmark(new LockValue<Integer>(), 20, 5);
        benchmark(new ReadWriteLockValue<Integer>(), 20, 5);
    }

    private static void benchmark(Value<Integer> v, int nReaders, int nWriters) {
        int loopCount = 10;
        String valueClass = v.getClass().getSimpleName();
        System.out.printf("開始 readers=%d, writers=%d, loop=%d, value=%s%n",
                          nReaders, nWriters, loopCount, valueClass);

        ExecutorService threadPool = Executors.newCachedThreadPool();
        Phaser phaser = new Phaser();
        phaser.register();
        for (int i = 0; i < nReaders; i++) {
            phaser.register();
            threadPool.execute(() -> {
                phaser.arriveAndAwaitAdvance();
                try {
                    for (int j = 0; j < loopCount; j++) {
                        v.read();
                    }
                } finally {
                    phaser.arriveAndDeregister();
                }
            });
        }
        for (int i = 0; i < nWriters; i++) {
            phaser.register();
            threadPool.execute(() -> {
                phaser.arriveAndAwaitAdvance();
                try {
                    for (int j = 0; j < loopCount; j++) {
                        v.write(j);
                    }
                } finally {
                    phaser.arriveAndDeregister();
                }
            });
        }
        phaser.arriveAndAwaitAdvance();
        long t = System.currentTimeMillis();
        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndDeregister();

        System.out.printf("終了 経過時間 = %dミリ秒%n%n", System.currentTimeMillis() - t);
        threadPool.shutdown();
    }
}
