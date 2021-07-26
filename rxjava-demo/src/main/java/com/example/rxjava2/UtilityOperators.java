package com.example.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class UtilityOperators {

    public static void main(String[] args) throws Exception {
        repeatDemo();
        System.out.println("---");
        delayDemo();
        System.out.println("---");
        doDemo();
        System.out.println("---");
        disposableDemo();
    }

    private static void repeatDemo() {

        // Flowableの生成
        Flowable<String> flowable = Flowable.just("A", "B", "C")
                                            .repeat(2);  // 通知を繰り返す

        // 購読する
        flowable.subscribe(new DebugSubscriber<>());
    }

    private static void delayDemo() throws Exception {

        // 処理の開始時間の出力
        System.out.println("処理開始： " + System.currentTimeMillis());

        // Flowableの生成
        Flowable<String> flowable =
                Flowable.<String>create(emitter -> {
                    // 購読の開始時間の出力
                    System.out.println("購読開始： " + System.currentTimeMillis());
                    // データの通知
                    emitter.onNext("A");
                    emitter.onNext("B");
                    emitter.onNext("C");
                    // 完了の通知
                    emitter.onComplete();
                }, BackpressureStrategy.BUFFER)
                        // 通知を遅らせる
                        .delay(2000L, TimeUnit.MILLISECONDS);

        // 購読する
        flowable.subscribe(data -> System.out.println(
                "通知時間： " + System.currentTimeMillis() + ": " + data));

        // しばらく待つ
        Thread.sleep(3000L);
    }

    private static void doDemo() throws Exception {

        Flowable.interval(500L, TimeUnit.MILLISECONDS)
                // 3件まで
                .take(3)
                // データ通知時のログ
                .doOnNext(data -> System.out.println("doOnNext: data=" + data))
                // 完了時のログ
                .doOnComplete(() -> System.out.println("doOnComplete"))
                // エラー時のログ
                .doOnError(error -> System.out.println("doOnError: error=" + error))
                // 購読開始時のログ
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
                // データ数のリクエスト時のログ
                .doOnRequest(size -> System.out.println("doOnRequest: size=" + size))
                // 購読解除時のログ
                .doOnCancel(() -> System.out.println("doOnCancel"))
                // 購読する
                .subscribe(new Subscriber<Long>() {

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("購読開始");
                        this.subscription = subscription;
                        subscription.request(1L);
                    }

                    @Override
                    public void onNext(Long data) {
                        System.out.println("データ=" + data);
                        subscription.request(1L);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.out.println("エラー=" + error);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("完了");
                    }
                });

        // しばらく待つ
        Thread.sleep(2000L);
    }

    private static void disposableDemo() throws Exception {

        Disposable disposable =
                Flowable.interval(500L, TimeUnit.MILLISECONDS)
                        // 5件まで
                        .take(5)
                        // データ通知時のログ
                        .doOnNext(data -> System.out.println("doOnNext: data=" + data))
                        // 完了時のログ
                        .doOnComplete(() -> System.out.println("doOnComplete"))
                        // エラー時のログ
                        .doOnError(error -> System.out.println("doOnError: error=" + error))
                        // 購読開始時のログ
                        .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
                        // データ数のリクエスト時のログ
                        .doOnRequest(size -> System.out.println("doOnRequest: size=" + size))
                        // 購読解除時のログ
                        .doOnCancel(() -> System.out.println("doOnCancel"))
                        // 購読する
                        .subscribeWith(new DebugSubscriber<>());

        // しばらく待った後に購読を解除する
        Thread.sleep(1000L);
        disposable.dispose();

        // しばらく待つ
        Thread.sleep(2000L);
    }
}
