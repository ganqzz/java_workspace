package com.example.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import java.util.concurrent.TimeUnit;

// observeOn()の引数
public class RxJava2Demo3 {

    public static void main(String[] args) throws Exception {

        Flowable<Long> flowable =
                // 100ミリ秒ごとに0から始まるデータを通知するFlowableを生成……（1）
                Flowable.interval(100L, TimeUnit.MILLISECONDS)
                        // BackpressureMode.DROPを設定した時と同じ挙動にする……（2）
                        .onBackpressureDrop();

        flowable
                // 非同期でデータを受け取るようにし、バッファサイズを1にする……（3）
                .observeOn(Schedulers.computation(), false, 1)
//				.observeOn(Schedulers.computation(), false, 2)
//				.observeOn(Schedulers.computation(), false, 10)
                // 購読する
                .subscribe(new DisposableSubscriber<Long>() {

                    // データを受け取った際の処理
                    @Override
                    public void onNext(Long item) {
                        // 300ミリ秒待つ……（4）
                        try {
                            Thread.sleep(300L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            // 異常終了で終わる
                            System.exit(1);
                        }

                        // 実行しているThread名の取得
                        String threadName = Thread.currentThread().getName();
                        // 受け取ったデータを出力する
                        System.out.println(threadName + ": " + item);
                    }

                    // 完了を通知された際の処理
                    @Override
                    public void onComplete() {
                        // 実行しているスレッド名の取得
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 完了しました");
                    }

                    // エラーを通知された際の処理
                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }
                });

        // しばらく待つ
        Thread.sleep(2000L);
    }
}
