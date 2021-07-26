package demo.concurrent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer版タイマー
 * TimerTaskは再利用できない
 * 基本的に、ScheduledExecutorServiceに優る点はない
 */
public class TimerDemo {

    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // ここに繰り返し処理を書く
                System.out.println("action.");
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 500);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel();

        System.out.println("---");

        timer = new Timer(); // 再利用はできないので再作成
        task = new TimerTask() { // TimerTaskも再利用できない！
            @Override
            public void run() {
                // ここに繰り返し処理を書く
                System.out.println("action.");
            }
        };
        timer.schedule(task, 0, 500);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel();
    }
}
