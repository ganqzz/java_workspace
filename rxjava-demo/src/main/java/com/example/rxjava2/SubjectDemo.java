package com.example.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Observable - Observer
 */
public class SubjectDemo {

    private static final CountDownLatch WAIT_LATCH = new CountDownLatch(1);

    public static void main(String... args) throws Exception {
        System.out.println("--- PublishSubject");
        publishSubjectDemo();
        System.out.println("--- BehaviorSubject");
        behaviorSubjectDemo();
        System.out.println("--- ReplaySubject");
        replaySubjectDemo();
        System.out.println("--- AsyncSubject");
        asyncSubjectDemo();
    }

    private static void asyncSubjectDemo() {
        Subject<Integer> source = AsyncSubject.create();

        source.subscribe(createObserver("First")); // it will emit only 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 4 and onComplete for second observer also.
         */
        source.subscribe(createObserver("Second"));

        source.onNext(4);
        source.onComplete();
    }

    private static void asyncSubjectDemo2() throws InterruptedException {
        Subject<String> subject = AsyncSubject.create();

        Observable.interval(0, 1, TimeUnit.SECONDS)
                  .take(4)
                  .map(Objects::toString)
                  .subscribe(subject);

        subject.subscribe(SubjectDemo::log);

        Thread.sleep(5100);

        subject.subscribe(SubjectDemo::log);

        Thread.sleep(2000);
    }

    private static void replaySubjectDemo() {
        Subject<Integer> source = ReplaySubject.create();

        source.subscribe(createObserver("First")); // it will get 1, 2, 3, 4

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();

        /*
         * it will emit 1, 2, 3, 4 for second observer also as we have used replay
         */
        source.subscribe(createObserver("Second"));
    }

    private static void replaySubjectDemo2() throws InterruptedException {
        Subject<String> subject = ReplaySubject.create();

        Observable.interval(0, 1, TimeUnit.SECONDS)
                  .map(Objects::toString)
                  .subscribe(subject);

        subject.subscribe(SubjectDemo::log);

        Thread.sleep(3100);

        subject.subscribe(SubjectDemo::log);

        Thread.sleep(2000);
    }

    private static void behaviorSubjectDemo() {
        Subject<Integer> source = BehaviorSubject.create();

        source.subscribe(createObserver("First")); // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 3(last emitted), 4 and onComplete for second observer also.
         */
        source.subscribe(createObserver("Second"));

        source.onNext(4);
        source.onComplete();
    }

    private static void publishSubjectDemo() throws InterruptedException {
        Subject<Integer> source = PublishSubject.create();

        source.subscribe(createObserver("First")); // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 4 and onComplete for second observer also.
         */
        source.subscribe(createObserver("Second"));

        source.onNext(4);
        source.onComplete();
    }

    private static void publishSubjectDemo2() throws InterruptedException {
        Subject<Long> subject = PublishSubject.create();

        Observable.interval(1, TimeUnit.SECONDS)
                  .take(10)
                  .doOnSubscribe((d) -> log("Original-doOnSubscribe"))
                  .doOnComplete(() -> log("Original-doOnComplete"))
                  .subscribe(subject);

        subject.doOnSubscribe((d) -> log("First-doOnSubscribe"))
               .doOnComplete(() -> log("First-doOnComplete"))
               .subscribe(v -> log("First: " + v));

        Thread.sleep(4100);

        subject.doOnSubscribe((d) -> log("Second-doOnSubscribe"))
               .doOnComplete(() -> log("Second-doOnComplete"))
               .subscribe(v -> log("Second: " + v));

        Thread.sleep(6000);
    }

    private static Observer<Integer> createObserver(String tag) {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(tag + ": onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println(tag + ": onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(tag + ": onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println(tag + ": onComplete");
            }
        };
    }

    private static void log(String stage, Object item) {
        System.out.println(stage + ": " + Thread.currentThread().getName() + ": " + item);
    }

    private static void log(Object item) {
        System.out.println(Thread.currentThread().getName() + ": " + item);
    }

    private static void log(Long v) {
        log(Objects.toString(v));
    }
}
