package com.example.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

/**
 * beta at 2.1.2
 *
 */
public class ParallelFlowableDemo {

    public static void main(String[] args) throws Exception {
        // 元となるFlowableからParallelFlowableを生成する
        ParallelFlowable<Integer> parallelFlowable =
                // 元となるFlowable
                Flowable.range(1, 5)
                        // 並列モードにする
                        .parallel()
                        // 異なるスレッド上で実行するようにする
                        .runOn(Schedulers.computation())
                        // 各データを10倍にする
                        .map(data -> data * 10)
                        // 通知処理をしているスレッドを出力
                        .doOnNext(data -> {
                            String threadName = Thread.currentThread().getName();
                            System.out.println("----- 通知: " + threadName + ": " + data);
                        });

        // ParallelFlowableをFlowableに変換する
        Flowable<Integer> flowable = parallelFlowable.sequential();

        // 購読する
        flowable.subscribe(
                // データ受取時の処理
                data -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": start: " + data);
                    Thread.sleep(10L);
                    System.out.println(threadName + ": end: " + data);
                },
                // エラー時の処理
                //e -> e.printStackTrace(),
                Throwable::printStackTrace,
                // 完了時の処理
                () -> System.out.println(Thread.currentThread().getName() + ": 完了"));

        // しばらく待つ
        Thread.sleep(1000L);
    }
}
