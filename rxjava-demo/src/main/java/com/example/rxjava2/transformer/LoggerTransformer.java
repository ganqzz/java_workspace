package com.example.rxjava2.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class LoggerTransformer<R> implements ObservableTransformer<R, R> {

    private final String tag;

    private LoggerTransformer(String tag) {
        this.tag = tag;
    }

    public static <R> LoggerTransformer<R> debugLog(String tag) {
        return new LoggerTransformer<>(tag);
    }

    @Override
    public ObservableSource<R> apply(Observable<R> upstream) {
        return upstream
                .doOnNext(v -> log("doOnNext", v))
                .doOnError(error -> log("doOnError", error))
                .doOnComplete(() -> log("doOnComplete", upstream))
                .doOnTerminate(() -> log("doOnTerminate", upstream))
                .doOnDispose(() -> log("doOnDispose", upstream))
                .doOnSubscribe(v -> log("doOnSubscribe", upstream));
    }

    private void log(String stage, Object item) {
        System.out.println(stage + ": " + Thread.currentThread().getName() + ": " + item);
    }

    private void log(String stage, Throwable throwable) {
        System.out.println(stage + ": " + Thread.currentThread().getName()
                           + ": error: " + throwable);
    }
}
