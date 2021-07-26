package linkedin;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 * acquire/release: might not be in the same thread
 * the difference to Lock or synchronized
 */
public class SemaphoreDemo {

    private static class CellPhone extends Thread {

        private static Semaphore charger = new Semaphore(0);

        public CellPhone(String name) {
            this.setName(name);
        }

        @Override
        public void run() {
            try {
                charger.acquire();
                System.out.println(getName() + " is charging...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(getName() + " is DONE charging!");
                charger.release();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            new CellPhone("Phone-" + i).start();
    }
}
