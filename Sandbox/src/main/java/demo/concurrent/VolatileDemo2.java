package demo.concurrent;

/**
 * volatile demo
 */
public class VolatileDemo2 {

    //private static int count = 0;
    private static volatile int count = 0;

    public static void main(String[] args) {
        Runnable display = () -> {
            int val = count;
            while (count < 10) {
                // volatileではない場合、最適化が行われ（countがループ内でアクセスされないためキャッシュされる）、
                // 下の判定は常にfalseになってしまう => 無限ループ
                if (val != count) {
                    String message = Thread.currentThread().getName() +
                                     ": val = " + val + ", count = " + count;
                    System.out.println(message + " updated");
                    val = count;
                }
            }
        };
        Runnable update = () -> {
            int val = count;
            while (count < 10) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String message = Thread.currentThread().getName() +
                                 ": val = " + val + ", count = " + count;
                System.out.println(message);
                count = ++val;
            }
        };

        new Thread(display, "display-thread").start();
        new Thread(update, "update-thread").start();
    }
}
