package com.example.rxjava2;

import io.reactivex.Flowable;

public class MethodChain {

    public static void main(String[] args) {
        Flowable<Integer> result = Flowable
                // 引数のデータを通知するFlowableを生成
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                // 偶数のデータのみを通知する
                .filter(data -> data % 2 == 0)
                // 通知するデータを10倍にする
                .map(data -> data * 10);

        // 購読する
        //result.subscribe(data -> System.out.println(data));
        result.subscribe(System.out::println);
    }
}
