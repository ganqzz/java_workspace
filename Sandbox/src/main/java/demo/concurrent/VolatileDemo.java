package demo.concurrent;

// Java言語仕様書参照

public class VolatileDemo {
    //private static boolean flag = false;
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            while (!flag) {
                // volatileを指定しない限り、最適化（キャッシュから読む）され無限ループになる
            }

            System.out.println("HELLO!");
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            flag = true; // write
            System.out.println("HI!");
        }).start();
    }
}
