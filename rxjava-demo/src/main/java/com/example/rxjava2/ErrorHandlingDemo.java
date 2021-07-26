package com.example.rxjava2;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class ErrorHandlingDemo {

    public static void main(String[] args) {

        // only unhandled error by ErrorHandler
        RxJavaPlugins.setErrorHandler(ErrorHandler.get());

        demo1();
        System.out.println("---");
        demo3();
        System.out.println("---");
        demo5();
        System.out.println("---");
        demo6();
        System.out.println("---");
        demo7();
        System.out.println("---");
        retryDemo();
    }

    private static void demo7() {
        Observable.<String>error(new Error("Crash!"))
                .onErrorReturnItem("ReturnItem") // handled
                .subscribe(item -> log("subscribe", item));
    }

    private static void demo6() {
        Observable.<String>error(new Error("Crash!"))
                .onErrorReturn(throwable -> "onErrorReturn: " + throwable) // handled
                .subscribe(item -> log("subscribe", item));
    }

    private static void demo5() {
        Observable.<String>error(new RuntimeException("Crash!"))
                .onExceptionResumeNext(Observable.just("Second")) // handled
                .subscribe(item -> log("subscribe", item));
    }

    private static void demo3() {
        Observable.just("One")
                  .doOnNext(i -> {
                      throw new RuntimeException("Very wrong");
                  })
                  .doOnError(e -> log("doOnError", e))
                  .subscribe(item -> log("subscribe", item), e -> log("subscribe", e)); // handled
    }

    private static void demo1() {
        // Unhandled Error
        Observable.just("One")
                  .doOnNext(i -> {
                      throw new RuntimeException("Unhandled Error");
                  })
                  .doOnError(e -> log("doOnError", e)) // handledにはならない
                  .subscribe(item -> log("subscribe", item));
    }

    private static void retryDemo() {
        Observable.just("1", "2", "3", "4", "5")
                  .subscribeOn(Schedulers.trampoline())
                  .retry()
                  .doOnNext(i -> log("doOnNext", i))
                  .subscribe(ErrorHandlingDemo::log);
    }

    private static void log(Object item) {
        System.out.println(Thread.currentThread().getName() + ": " + item);
    }

    private static void log(String stage, Object item) {
        System.out.println(stage + ": " + Thread.currentThread().getName() + ": " + item);
    }

    private static void log(String stage, Throwable throwable) {
        System.out.println(stage + ": " + Thread.currentThread().getName()
                           + ": error: " + throwable);
    }

    /**
     * ErrorHandler
     */
    private static class ErrorHandler implements Consumer<Throwable> {

        private static final ErrorHandler INSTANCE = new ErrorHandler();

        public static ErrorHandler get() {
            return INSTANCE;
        }

        private ErrorHandler() {
        }

        @Override
        public void accept(Throwable throwable) throws Exception {
            System.out.println("Error on " + Thread.currentThread().getName() + ": " + throwable);
        }
    }
}
