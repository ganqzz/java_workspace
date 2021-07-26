package com.example.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;
import org.reactivestreams.Processor;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Flowable - Subscriber
 *
 */
public class ProcessorDemo {

    private static final CountDownLatch WAIT_LATCH = new CountDownLatch(1);

    public static void main(String... args) throws Exception {
    }

    private static void demo17() throws InterruptedException {
        //Processor<T, R> = PublishProcessor<T>.create()
        Flowable<String> flowable;
    }

    public static void log(String stage, Object item) {
        System.out.println(stage + ": " + Thread.currentThread().getName() + ": " + item);
    }

    public static void log(Object item) {
        System.out.println(Thread.currentThread().getName() + ": " + item);
    }

    private static void log(Long v) {
        log(Objects.toString(v));
    }
}
