package com.example.rxjava2;

import io.reactivex.Flowable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class AsyncDemo {

    public static void main(String[] args) throws Exception {
        flatMapDemo();
        System.out.println("---");
        concatMapDemo();
        System.out.println("---");
        concatMapEagerDemo();
    }

    public static void flatMapDemo() throws Exception {
        Flowable<String> flowable =
                // Flowableの生成
                Flowable.just("A", "B", "C")
                        // 受け取ったデータからFlowableを生成し、それが持つデータを通知する
                        .flatMap(data -> {
                            // 1000ミリ秒遅れてデータを通知するFlowableを生成
                            return Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS);
                        });

        // 購読する
        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + data);
        });

        // しばらく待つ
        Thread.sleep(2000L);
    }

    public static void concatMapDemo() throws Exception {
        Flowable<String> flowable =
                // Flowableの生成
                Flowable.just("A", "B", "C")
                        // 受け取ったデータからFlowableを生成し、それが持つデータを通知する
                        .concatMap(data -> {
                            // 1000ミリ秒遅れてデータを通知するFlowableを生成
                            return Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS);
                        });

        // 購読する
        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            String time =
                    LocalTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"));
            System.out.println(threadName + ": data=" + data + ", time=" + time);
        });

        // しばらく待つ
        Thread.sleep(4000L);
    }

    public static void concatMapEagerDemo() throws Exception {
        Flowable<String> flowable =
                // Flowableの生成
                Flowable.just("A", "B", "C")
                        // 受け取ったデータからFlowableを生成し、それが持つデータを通知する
                        .concatMapEager(data -> {
                            // 1000ミリ秒遅れてデータを通知するFlowableを生成
                            return Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS);
                        });

        // 購読する
        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            String time =
                    LocalTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"));
            System.out.println(threadName + ": data=" + data + ", time=" + time);
        });

        // しばらく待つ
        Thread.sleep(2000L);
    }
}
