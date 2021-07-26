package linkedin;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Not Data races but Race Condition
 * Mutexで、データの書き込み・読み込みは保証するけど、それらの順序までは保証しない
 */
public class RaceConditionDemo {

    private static class Shopper extends Thread {

        public static int bagsOfChips = 1; // start with one on the list
        private static Lock pencil = new ReentrantLock();

        public Shopper(String name) {
            this.setName(name);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1); // do a bit of work first
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.getName().contains("Olivia")) {
                pencil.lock();
                try {
                    bagsOfChips += 3;
                    System.out.println(this.getName() + " ADDED three bags of chips.");
                } finally {
                    pencil.unlock();
                }
            } else { // "Barron"
                pencil.lock();
                try {
                    bagsOfChips *= 2;
                    System.out.println(this.getName() + " DOUBLED the bags of chips.");
                } finally {
                    pencil.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // create 10 shoppers: Barron-0...4 and Olivia-0...4
        Shopper[] shoppers = new Shopper[10];
        for (int i = 0; i < shoppers.length / 2; i++) {
            shoppers[2 * i] = new Shopper("Barron-" + i);
            shoppers[2 * i + 1] = new Shopper("Olivia-" + i);
        }
        for (Shopper s : shoppers)
            s.start();
        for (Shopper s : shoppers)
            s.join();
        System.out.println("We need to buy " + Shopper.bagsOfChips + " bags of chips.");
    }
}
