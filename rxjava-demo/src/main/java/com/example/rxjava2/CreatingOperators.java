package com.example.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CreatingOperators {

    // 「分:秒.ミリ秒」の文字列に変換するFormatter
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

    public static void main(String[] args) throws Exception {
        justDemo();
        System.out.println("---");
        fromArrayDemo();
        System.out.println("---");
        intervalDemo();
        System.out.println("---");
        timerDemo();
    }

    private static void justDemo() {

        // 引数のデータを順に通知するFlowableの生成
        Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E");

        // 購読開始
        flowable.subscribe(new DebugSubscriber<>());
    }

    private static void fromArrayDemo() {

        // 引数のデータを順に通知するFlowableの生成
        Flowable<String> flowable = Flowable.fromArray("A", "B", "C", "D", "E");
        Flowable<String> flowable2 = Flowable.fromIterable(Arrays.asList("V", "W", "X", "Y", "Z"));

        // 購読開始（同期）
        flowable.subscribe(new DebugSubscriber<>());
        flowable2.subscribe(new DebugSubscriber<>());
    }

    private static void intervalDemo() throws Exception {

        // 1000ミリ秒ごとに数値を通知するFlowableの生成
        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS);

        // 処理を開始する前の時間
        System.out.println("開始時間: " + LocalTime.now().format(formatter));

        // 購読する
        Disposable disposable = flowable.subscribe(data -> {
            // Thread名の取得
            String threadName = Thread.currentThread().getName();
            // 現在時刻の「分:秒.ミリ秒」を取得
            String time = LocalTime.now().format(formatter);
            // 出力
            System.out.println(threadName + ": " + time + ": data=" + data);
        });

        // しばらく待つ
        Thread.sleep(3000L);
        disposable.dispose();
    }

    private static void timerDemo() throws Exception {

        // 処理を開始する前の時間
        System.out.println("開始時間: " + LocalTime.now().format(formatter));

        // 1000ミリ秒後に数値「0」を通知するFlowableの生成
        Flowable<Long> flowable = Flowable.timer(2000L, TimeUnit.MILLISECONDS);

        // 購読開始
        Disposable disposable = flowable.subscribe(
                // 第1引数： データの通知時
                data -> {
                    // Thread名の取得
                    String threadName = Thread.currentThread().getName();
                    // 現在時刻の「分:秒.ミリ秒」を取得
                    String time = LocalTime.now().format(formatter);
                    // 出力
                    System.out.println(threadName + ": " + time + ": data=" + data);
                },
                // 第2引数： エラーの通知時
                error -> System.out.println("エラー=" + error),
                // 第3引数： 完了の通知時
                () -> System.out.println("完了"));

        // しばらく待つ
        Thread.sleep(3000L);
        //disposable.dispose();
    }
}
