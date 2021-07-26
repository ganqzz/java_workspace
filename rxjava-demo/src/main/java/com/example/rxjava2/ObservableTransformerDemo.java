package com.example.rxjava2;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static com.example.rxjava2.transformer.FileCacheObservableTransformer.cacheToLocalFileNamed;
import static com.example.rxjava2.transformer.LoggerTransformer.debugLog;
import static com.example.rxjava2.transformer.TimingObservableTransformer.timeItems;

/**
 * Using ObservableTransformer demo
 */
public class ObservableTransformerDemo {

    public static void main(String[] args) throws Exception {
        cachingExample();
        System.out.println("---");
        timingExample2();
        System.out.println("---");
        debugDemo();
    }

    private static void debugDemo() throws InterruptedException {
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                                          .compose(debugLog("afterInterval"))
                                          .flatMap(v -> Observable.just("items"))
                                          .compose(debugLog("afterFlatMap"))
                                          .subscribe();
        Thread.sleep(3000);
        disposable.dispose();
    }

    private static void timingExample2() throws InterruptedException {
        final Observable<Long> observable =
                Observable.interval(1, TimeUnit.SECONDS)
                          .take(2)
                          .compose(timeItems(
                                  seconds -> log("Seconds passed since the start: " + seconds)));

        observable.subscribe(ObservableTransformerDemo::log);
        observable.subscribe(ObservableTransformerDemo::log);
        Thread.sleep(3000);
    }

    private static void timingExample() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                  .take(2)
                  .compose(timeItems(seconds -> log("Seconds passed since the start: " + seconds)))
                  .subscribe(ObservableTransformerDemo::log);
        Thread.sleep(3000);
    }

    private static void cachingExample() {
        Observable.just("1")
                  .compose(cacheToLocalFileNamed("test"))
                  .subscribe(ObservableTransformerDemo::log);
    }

    private static void log(Throwable throwable) {
        System.out.println("Error: " + Thread.currentThread().getName() + ": " + throwable);
    }

    private static void log(String stage, Object item) {
        System.out.println(stage + ": " + Thread.currentThread().getName() + ": " + item);
    }

    private static void log(Object data) {
        System.out.println(Thread.currentThread().getName() + ": " + data);
    }
}
