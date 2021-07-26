package demo.concurrent.problems;

public class DeadlockDemo1 {

    private static class Task {

        // Key（Monitor）は2個
        private final Object key1 = new Object();
        private final Object key2 = new Object();

        public void a() {
            synchronized (key1) {
                System.out.println("[" + Thread.currentThread().getName() + "] I am in a()");
                try { // 最近のマシンのためのウェイトを入れる
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b();
            }
        }

        public void b() {
            synchronized (key2) {
                System.out.println("[" + Thread.currentThread().getName() + "] I am in b()");
                c();
            }
        }

        public void c() {
            synchronized (key1) {
                System.out.println("[" + Thread.currentThread().getName() + "] I am in c()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task(); // オブジェクトは1個

        Runnable r1 = task::a;
        Runnable r2 = task::b;

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Done!");
    }
}
