package demo.concurrent;

/**
 * Not suitable for transactional tasks
 */
public class DaemonThreadsDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() START");

        Runnable r = new Runnable() {

            @Override
            public void run() {
                System.out.println("  " + Thread.currentThread().getName() +
                                   ": START: isDaemon? = " + Thread.currentThread().isDaemon());

                // start child thread
                new Thread(() -> {
                    System.out.println("    " + Thread.currentThread().getName() +
                                       ": START: isDaemon? = " + Thread.currentThread().isDaemon()
                    ); // inherit from parent's daemon status
                }, "child").start();

                for (int i = 1; i <= 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("  " + Thread.currentThread().getName() + ": loop: " + i);
                }

                System.out.println("  " + Thread.currentThread().getName() + ": END");
            }
        };

        Thread t = new Thread(r, "parent");
        t.setDaemon(true); // daemon thread : start()より先に呼ぶ必要がある。
        t.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException x) {}

        System.out.println(Thread.currentThread().getName() + ": main() END");
    }
}
