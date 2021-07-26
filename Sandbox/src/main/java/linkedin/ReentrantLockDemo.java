package linkedin;

import java.util.concurrent.locks.ReentrantLock;

/**
 * lock/unlock: must be in the same thread
 * just like synchronized blocks
 */
public class ReentrantLockDemo {

    static class Shopper extends Thread {

        static int garlicCount, potatoCount = 0;
        static ReentrantLock pencil = new ReentrantLock();

        private void addGarlic() {
            pencil.lock();
            System.out.println("Hold count: " + pencil.getHoldCount());
            garlicCount++;
            pencil.unlock();
        }

        private void addPotato() {
            pencil.lock();
            potatoCount++;
            addGarlic(); // reentrant
            pencil.unlock();
        }

        @Override
        public void run() {
            for (int i = 0; i < 10_000; i++) {
                addGarlic();
                addPotato();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
        System.out.println("We should buy " + Shopper.potatoCount + " potatoes.");
    }
}
