package com.example.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.TimeUnit;

public class Sandbox {

    public static void main(String[] args) throws Exception {
        flowableDemo();
        bufferDemo();
        doOperatorsDemo();
        disposableDemo();
    }

    private static void disposableDemo() {
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                                          .doOnDispose(() -> System.out.println("onDispose: 0"))
                                          .subscribe();
        disposable.dispose();

        Disposable disposable1 = Observable.interval(1, TimeUnit.SECONDS)
                                           .doOnDispose(() -> System.out.println("onDispose: 1"))
                                           .subscribe();
        Disposable disposable2 = Observable.interval(1, TimeUnit.SECONDS)
                                           .doOnDispose(() -> System.out.println("onDispose: 2"))
                                           .subscribe();

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.addAll(disposable1, disposable2);
        compositeDisposable.clear();
    }

    // 副作用を発生させる
    private static void doOperatorsDemo() throws InterruptedException {
        System.out.println("-- Observable --");
        Disposable disposable1 = Observable.just("a", "b")
                                           .doOnSubscribe(e -> log("doOnSubscribe"))
                                           .doOnNext(e -> log("doOnNext", e))
                                           .doOnEach(e -> log("doOnEach"))
                                           .doOnComplete(() -> log("doOnComplete"))
                                           .doOnDispose(() -> log("doOnDispose"))
                                           .doOnTerminate(() -> log("doOnTerminate"))
                                           .doFinally(() -> log("doFinally"))
                                           .doOnError(e -> log("doOnError"))
                                           .subscribe(e -> log("subscribe", e));

        System.out.println("\n-- Flowable --");
        Disposable disposable2 = Flowable.just("a", "b")
                                         .doOnSubscribe(e -> log("doOnSubscribe"))
                                         .doOnRequest(e -> log("doOnRequest", e))
                                         .doOnNext(e -> log("doOnNext", e))
                                         .doOnEach(e -> log("doOnEach"))
                                         .doOnComplete(() -> log("doOnComplete"))
                                         .doOnCancel(() -> log("doOnCancel"))
                                         .doOnTerminate(() -> log("doOnTerminate"))
                                         .doFinally(() -> log("doFinally"))
                                         .doOnError(e -> log("doOnError"))
                                         .subscribe(e -> log("subscribe", e));

        Thread.sleep(2000);
        disposable1.dispose();
        disposable2.dispose();
    }

    // 通知なし1回限り(onComplete/onError)
    private static void completableDemo() {
        Completable.complete()
                   .subscribe(() -> {}, throwable -> {});

        Completable.create(emitter -> {
            try {
                // 何らかの処理

                // 完了
                emitter.onComplete();
            } catch (Exception ex) {
                emitter.onError(ex);
            }
        }).subscribe(new DisposableCompletableObserver() {
            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    // 1回限り(onSuccess/onError)
    private static void singleDemo() {
        Single.just("Hoge")
              .subscribe(s -> {}, throwable -> {});

        Single.<String>create(emitter -> {
            try {
                // 一個emitして完了
                emitter.onSuccess("Single Hello");
            } catch (Exception ex) {
                emitter.onError(ex);
            }
        }).subscribe(new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    // 1回限り(onComplete/onSuccess/onError)
    private static void maybeDemo() {
        Maybe.empty()
             .subscribe(object -> {}, throwable -> {}, () -> {});

        Maybe.<String>create(emitter -> {
            try {
                // 何らかの処理
                String result = "Maybe Hello";

                if (result == null || result.isEmpty()) {
                    // アイテムなしの場合はそのまま完了
                    emitter.onComplete();
                    return;
                }

                // 一個流して完了
                emitter.onSuccess(result);
            } catch (Exception ex) {
                emitter.onError(ex);
            }
        }).subscribe(new DisposableMaybeObserver<String>() {
            @Override
            public void onSuccess(String value) {
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private static void bufferDemo() {
        Subject<Integer> subject = PublishSubject.create();

        subject.toFlowable(BackpressureStrategy.MISSING)
               .buffer(10)
               .observeOn(Schedulers.computation())
               .subscribe(v -> log("s", v), Sandbox::log);

        for (int i = 0; i < 1000000; i++) {
            subject.onNext(i);
        }
    }

    private static void flowableDemo() {
        Observable.just("One", "Two")
                  .toFlowable(BackpressureStrategy.DROP)
                  .onBackpressureDrop()
                  .buffer(10);
    }

    private static void log(Throwable throwable) {
        System.out.println("Error: " + Thread.currentThread().getName() + ": " + throwable);
    }

    private static void log(String stage, Object item) {
        System.out.println(stage + ": " + Thread.currentThread().getName() + ": " + item);
    }

    private static void log(String stage) {
        System.out.println(stage + ": " + Thread.currentThread().getName());
    }
}
