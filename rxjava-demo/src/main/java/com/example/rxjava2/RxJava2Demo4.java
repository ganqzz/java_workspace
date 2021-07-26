package com.example.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

// Observable　バックプレッシャーなし
public class RxJava2Demo4 {

    public static void main(String[] args) throws Exception {

        // あいさつの言葉を通知するObservableの生成
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter)
                    throws Exception {

                // 通知するデータ
                String[] datas = {"Hello, World!", "こんにちは、世界！"};

                for (String data : datas) {
                    // 解除されている場合は処理をやめる
                    if (emitter.isDisposed()) {
                        return;
                    }

                    // データを通知する
                    emitter.onNext(data);
                }

                // 完了したことを通知する
                emitter.onComplete();
            }
        });

        observable
                // 消費する側の処理を別スレッドで行うようにする
                .observeOn(Schedulers.computation())
                // 購読する
                .subscribe(new Observer<String>() {

                    // subscribeメソッドが呼ばれた際の処理
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        // 何もしない
                    }

                    // データを受け取った際の処理
                    @Override
                    public void onNext(String item) {
                        // 実行しているThread名の取得
                        String threadName = Thread.currentThread().getName();
                        // 受け取ったデータを出力する
                        System.out.println(threadName + ": " + item);
                    }

                    // 完了を通知された際の処理
                    @Override
                    public void onComplete() {
                        // 実行しているThread名の取得
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
        Thread.sleep(500L);
    }
}
