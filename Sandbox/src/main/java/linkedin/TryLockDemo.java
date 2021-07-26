package linkedin;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {

    /**
     * pessimistic locking
     */
    private static class Shopper extends Thread {

        private int itemsToAdd = 0; // items this shopper is waiting to add
        private static int itemsOnNotepad = 0; // total items on shared notepad
        private static Lock pencil = new ReentrantLock();

        public Shopper(String name) {
            setName(name);
        }

        @Override
        public void run() {
            while (itemsOnNotepad <= 20) {
                if (itemsToAdd > 0) { // add item(s) to shared notepad
                    try {
                        pencil.lock();
                        itemsOnNotepad += itemsToAdd;
                        System.out.println(
                                getName() + " added " + itemsToAdd + " item(s) to notepad.");
                        itemsToAdd = 0;
                        Thread.sleep(300); // time spent writing
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        pencil.unlock();
                    }
                } else { // look for other things to buy
                    try {
                        Thread.sleep(100); // time spent searching
                        itemsToAdd++;
                        System.out.println(getName() + " found something to buy.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * optimistic locking
     */
    private static class ShopperTry extends Thread {

        private int itemsToAdd = 0; // items this shopper is waiting to add
        private static int itemsOnNotepad = 0; // total items on shared notepad
        private static Lock pencil = new ReentrantLock();

        public ShopperTry(String name) {
            setName(name);
        }

        @Override
        public void run() {
            while (itemsOnNotepad <= 20) {
                if (itemsToAdd > 0 && pencil.tryLock()) { // add item(s) to shared notepad
                    try {
                        itemsOnNotepad += itemsToAdd;
                        System.out.println(
                                getName() + " added " + itemsToAdd + " item(s) to notepad.");
                        itemsToAdd = 0;
                        Thread.sleep(300); // time spent writing
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        pencil.unlock();
                    }
                } else { // look for other things to buy
                    try {
                        Thread.sleep(100); // time spent searching
                        itemsToAdd++;
                        System.out.println(getName() + " found something to buy.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        demo(new Shopper("Barron"), new Shopper("Olivia"));
        System.out.println("---");
        demo(new ShopperTry("Barron"), new ShopperTry("Olivia"));
    }

    public static void demo(Thread... threads) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (finish - start) / 1000.0 + " seconds");
    }
}
