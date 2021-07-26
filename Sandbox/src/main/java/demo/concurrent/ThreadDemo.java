package demo.concurrent;

public class ThreadDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); // main thread

        Thread anotherThread = new Thread() {
            @Override
            public void run() {
                System.out.println(getName());
                for (int i = 5; i >= 0; i--) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
            }
        };
        anotherThread.setName("Another");
        anotherThread.start();

        try {
            anotherThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---");

        // Stop the Thread
        Runnable runnable = () -> {
            // 無限ループの場合の止め方
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Doing something...");

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted in sleeping(TIMED_WAITING)");
                    //Thread.currentThread().interrupt(); // 必要であれば、次のWAITINGのために投げ直す
                    break; // ここ
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt(); // *
        t2.interrupt(); // *

        // 終了まで待つ
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}
