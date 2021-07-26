package demo.concurrent.locks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class ParkUnparkDemo {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        AtomicBoolean canProceed = new AtomicBoolean();
        new Thread(() -> {
            sleep(5_000L);

            log("unpark(1回目)前");
            LockSupport.unpark(mainThread);
            log("unpark(1回目)後");

            sleep(5_000L);
            canProceed.set(true);

            log("unpark(2回目)前");
            LockSupport.unpark(mainThread);
            log("unpark(2回目)後");
        }).start();

        // parkの呼び出し前にunparkでパーミットをとっておくことができる
        LockSupport.unpark(mainThread);

        // パーミットは同時に2つ以上取れないのでこれは無視される
        LockSupport.unpark(mainThread);

        while (!canProceed.get()) {
            log("park前");
            LockSupport.park(); // 1回目は取得済みパーミットを消費して通過
            log("park後");
        }

        log("ループを抜けて終了");
    }

    private static void log(String fmt, Object... args) {
        String now = String.format("%1$tT.%1$tL", System.currentTimeMillis());
        now = "--:" + now.substring(3);
        System.out.printf("%s [%s] %s%n", now, Thread.currentThread().getName(),
                          String.format(fmt, args));
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
