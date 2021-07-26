package com.example.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.FutureTask;

public class ObservableCreationDemo {

    public static void main(String[] args) {

        Observable.defer(() -> {
            try {
                return Observable.just(longRunningOperation());
            } catch (Exception e) {
                return Observable.error(e);
            }
        })
                  .subscribeOn(Schedulers.io());

        Observable.fromFuture(new FutureTask<>(ObservableCreationDemo::longRunningOperation))
                  .subscribeOn(Schedulers.io());

        // Very handy
        Observable.fromCallable(ObservableCreationDemo::longRunningOperation)
                  .subscribeOn(Schedulers.io());

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            }
        });

        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) throws Exception {
            }
        }, BackpressureStrategy.DROP);

        // lambda
        Observable.create(e -> {
            try {
                e.onNext(2);
                e.onNext(99);
                e.onNext("Hoge");
            } catch (Exception ex) {
                e.onError(ex);
            } finally {
                e.onComplete();
            }
        });
    }

    public static String longRunningOperation() {
        return "";
    }
}
