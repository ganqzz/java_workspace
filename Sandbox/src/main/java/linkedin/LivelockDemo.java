package linkedin;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A polite philosopher makes sure their friends eat first
 */
public class LivelockDemo {

    private static class Philosopher extends Thread {

        private Lock firstChopstick, secondChopstick;
        private static int sushiCount = 100;
        private Random rps = new Random();

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
                if (!secondChopstick.tryLock()) { // if fail
                    System.out.println(getName() + " released their first chopstick.");

                    firstChopstick.unlock(); // release first lock

                    // 次回譲り合いになり難くするため、ランダム時間のウェイトを入れる
                    // このウェイトをなくすとLivelockが発生する
                    try {
                        Thread.sleep(rps.nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        // take a piece of sushi
                        if (sushiCount > 0) {
                            sushiCount--;
                            System.out.println(getName() + " took a piece! Sushi remaining: " +
                                               sushiCount);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // put down chopsticks
                        secondChopstick.unlock();
                        firstChopstick.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickC, chopstickA).start();
    }
}
