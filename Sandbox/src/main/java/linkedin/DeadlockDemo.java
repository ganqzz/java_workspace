package linkedin;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Dead lock and abandoned lock demo
 * Three philosophers, thinking and eating sushi
 */
public class DeadlockDemo {

    private static class Philosopher extends Thread {

        private Lock firstChopstick, secondChopstick;
        private static int sushiCount = 100;

        public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
            this.setName(name);
            this.firstChopstick = firstChopstick;
            this.secondChopstick = secondChopstick;
        }

        @Override
        public void run() {
            while (sushiCount > 0) { // eat sushi until it's all gone

                // pick up the first chopstick
                firstChopstick.lock();

                try { // スレッドがばらけるようにウェイト
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // pick up the second chopstick
                secondChopstick.lock();

                try {
                    // take a piece of sushi
                    if (sushiCount > 0) {
                        sushiCount--;
                        System.out.println(
                                getName() + " took a piece! Sushi remaining: " + sushiCount);
                    }

                    //if (sushiCount == 10) System.out.println(1 / 0); // exception

                } finally { // 確実にunlockする
                    // put down chopsticks
                    secondChopstick.unlock();
                    firstChopstick.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        deadLock();
        //noDeadLock();
    }

    public static void deadLock() {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickC, chopstickA).start(); // *
    }

    public static void noDeadLock() {
        // Lock ordering(Priority): A -> B -> C
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickA, chopstickC).start(); // *
    }
}
