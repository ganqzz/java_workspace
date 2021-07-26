package demo.concurrent.problems;

/**
 * Data races precisely
 */
public class RaceCondition {

    private static class LongWrapper {

        private final Object mutex = new Object();
        private volatile long l = 0;

        LongWrapper() {
        }

        LongWrapper(long l) {
            this.l = l;
        }

        long getValue() {
            return l; // 1op(atomic)なのでvolatileで十分
        }

        void incrementValue() {
            try { // ばらけさせるためのウェイトを入れる
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ++l; // l = l + 1 non-atomic operation (LOAD, ADD and PUT): insufficient only volatile
        }

        void incrementValueSync() {
            synchronized (mutex) {
                incrementValue();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- no synchronized");
        for (int j = 0; j < 5; j++) {
            noSyncDemo();
        }

        System.out.println("--- synchronized");
        for (int j = 0; j < 5; j++) {
            syncDemo();
        }
    }

    private static void noSyncDemo() {
        LongWrapper longWrapper = new LongWrapper();

        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                longWrapper.incrementValue();
            }
        };

        run(longWrapper, r);
    }

    private static void syncDemo() {
        LongWrapper longWrapper = new LongWrapper();

        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                longWrapper.incrementValueSync();
            }
        };

        run(longWrapper, r);
    }

    private static void run(LongWrapper longWrapper, Runnable r) {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Value = " + longWrapper.getValue());
    }
}
