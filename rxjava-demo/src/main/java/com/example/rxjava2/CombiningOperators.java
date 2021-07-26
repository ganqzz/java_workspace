package com.example.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CombiningOperators {

    public static void main(String[] args) throws Exception {
        concatDemo();
        System.out.println("---");
        mergeDemo();
        System.out.println("---");
        combineLatestDemo();
        System.out.println("---");
        zipDemo();
    }

    private static void concatDemo() throws InterruptedException {
        final Observable<String> aObservable = Observable.fromArray("A1", "A2", "A3", "A4");
        final Observable<String> bObservable = Observable.fromArray("B1", "B2", "B3");
        Observable.concat(aObservable, bObservable)
                  .subscribe(CombiningOperators::log);

        Thread.sleep(5000);
    }

    private static void mergeDemo() throws Exception {

        Flowable<Long> flowable1 =
                Flowable.interval(300L, TimeUnit.MILLISECONDS)  // 300ミリ秒ごとにデータを通知する
                        .take(5);  // 5件まで

        Flowable<Long> flowable2 =
                Flowable.interval(500L, TimeUnit.MILLISECONDS)  // 500ミリ秒ごとにデータを通知する
                        .take(2)  // 2件まで
                        .map(data -> data + 100L);  // 100加算する

        // 複数のFlowableをマージする
        Flowable<Long> result = Flowable.merge(flowable1, flowable2);

        // 購読する
        result.subscribe(new DebugSubscriber<>());

        // しばらく待つ
        Thread.sleep(2000L);
    }

    private static void combineLatestDemo() throws Exception {

        Flowable<Long> flowable1 =
                Flowable.interval(300L, TimeUnit.MILLISECONDS)  // 300ミリ秒ごとにデータを通知する
                        .take(5);  // 5件まで

        Flowable<Long> flowable2 =
                Flowable.interval(500L, TimeUnit.MILLISECONDS)  // 500ミリ秒ごとにデータを通知する
                        .take(2)  // 2件まで
                        .map(data -> data + 100L);  // 100加算する

        // 複数のFlowableから受け取ったデータで新しいデータを生成する
        Flowable<List<Long>> result =
                Flowable.combineLatest(
                        // 結合するFlowable
                        flowable1,
                        // 結合するFlowable
                        flowable2,
                        // 引数より通知されたデータをListに格納して通知
                        (data1, data2) -> Arrays.asList(data1, data2)
                );

        // 購読する
        result.subscribe(new DebugSubscriber<>());

        // しばらく待つ
        Thread.sleep(2000L);
    }

    private static void zipDemo() throws Exception {

        Flowable<Long> flowable1 =
                Flowable.interval(300L, TimeUnit.MILLISECONDS)  // 300ミリ秒ごとにデータを通知する
                        .take(5);  // 5件まで

        Flowable<Long> flowable2 =
                Flowable.interval(500L, TimeUnit.MILLISECONDS)  // 500ミリ秒ごとにデータを通知する
                        .take(3)  // 3件まで
                        .map(data -> data + 100L);  // 100加算する

        // 複数のFlowableから受け取ったデータで新しいデータを生成する
        Flowable<List<Long>> result = Flowable.zip(
                // 結合するFlowable
                flowable1,
                // 結合するFlowable
                flowable2,
                // 引数より通知されたデータをListに格納して通知
                (data1, data2) -> Arrays.asList(data1, data2)
        );

        // 購読する
        result.subscribe(new DebugSubscriber<>());

        // しばらく待つ
        Thread.sleep(2000L);
    }

    private static void log(Object data) {
        System.out.println(Thread.currentThread().getName() + ": " + data);
    }
}
