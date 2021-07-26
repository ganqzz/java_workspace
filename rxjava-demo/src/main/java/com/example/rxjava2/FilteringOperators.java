package com.example.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class FilteringOperators {

    public static void main(String[] args) throws Exception {
        filterDemo();
        System.out.println("---");
        takeDemo();
        System.out.println("---");
        skipDemo();
        System.out.println("---");
        distinctUntilChangedDemo();
        System.out.println("---");
        debounceDemo();
    }

    private static void filterDemo() throws Exception {

        // Flowableの生成
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                                          .filter(data -> data % 2 == 0);  // 偶数のみ通知する

        // 購読する
        flowable.subscribe(new DebugSubscriber<>());

        // しばらく待つ
        Thread.sleep(3000L);
    }

    private static void takeDemo() throws Exception {

        // Flowableの生成
        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                                          .take(3);  // 3件まで通知する

        // 購読する
        flowable.subscribe(new DebugSubscriber<>());

        // しばらく待つ
        Thread.sleep(4000L);
    }

    private static void skipDemo() throws Exception {

        // Flowableの生成
        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                                          .skip(2);  // 最初の2件は通知しない

        // 購読する
        flowable.subscribe(new DebugSubscriber<>());

        // しばらく待つ
        Thread.sleep(5000L);
    }

    private static void distinctUntilChangedDemo() {

        // Flowableの生成
        Flowable<String> flowable = Flowable.just("A", "a", "a", "A", "a")
                                            .distinctUntilChanged();  // 連続して重複したデータを除いて通知する

        // 購読する
        flowable.subscribe(new DebugSubscriber<>());
    }

    private static void debounceDemo() {

        // Flowableの生成
        Flowable<String> flowable = Flowable.<String>create(
                // 通知処理
                emitter -> {
                    // データを通知し、しばらく待つ
                    emitter.onNext("A");
                    Thread.sleep(1000L);

                    emitter.onNext("B");
                    Thread.sleep(300L);

                    emitter.onNext("C");
                    Thread.sleep(300L);

                    emitter.onNext("D");
                    Thread.sleep(1000L);

                    emitter.onNext("E");
                    Thread.sleep(100L);

                    // 完了を通知
                    emitter.onComplete();
                }, BackpressureStrategy.BUFFER)
                .throttleWithTimeout(500L, TimeUnit.MILLISECONDS);  // 指定した期間に次のデータが来なければ通知する

        // 購読する
        flowable.subscribe(new DebugSubscriber<>());
    }
}
