package com.example.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SchedulerDemo {

    private static final CountDownLatch WAIT_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        subscribeOn_observeOn();
        demo1();
        subscribeOnDemo();
        subscribeOnDemo2();
        //schedulerFromExecutor();
    }

    private static void subscribeOn_observeOn() throws InterruptedException {
        // subscribeOn, observeOn
        Observable.<String>create(emitter -> {
            System.out.println("ObservableOnSubscribe: " + Thread.currentThread().getName());
            emitter.onNext("data1");
        })
                .doOnNext(data -> log("doOnNext", data))
                .observeOn(Schedulers.single())
                .doOnNext(data -> log("doOnNext", data))
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.newThread())
                .subscribe(data -> log("subscribe", data));
        // しばらく待つ
        Thread.sleep(500);
    }

    private static void demo1() throws InterruptedException {
        // subscribeOnに何も指定しない場合
        Flowable.just(1, 2, 3, 4, 5) // Flowableの設定
                .subscribe(SchedulerDemo::log);
        // しばらく待つ
        Thread.sleep(100);
    }

    private static void subscribeOnDemo() throws InterruptedException {
        // justは指定されていないので、subscribeOnでの指定が可能（先勝ち）
        Flowable.just(1, 2, 3, 4, 5) // Flowableの設定
                .subscribeOn(Schedulers.single()) // RxSingleScheduler
                .subscribeOn(Schedulers.io()) // RxCachedThreadScheduler
                .subscribeOn(Schedulers.computation()) // RxComputationThreadPool
                .subscribe(SchedulerDemo::log);
        // しばらく待つ
        Thread.sleep(100);
    }

    private static void subscribeOnDemo2() throws InterruptedException {
        // intervalはComputation指定済みなので、変更できない。
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(5)
                .subscribeOn(Schedulers.single()) // 無視される
                .subscribe(SchedulerDemo::log);
        // しばらく待つ
        Thread.sleep(3000L);
    }

    private static void schedulerFromExecutor() throws Exception {
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        final Scheduler pooledScheduler = Schedulers.from(executor);

        Observable.range(1, 100)
                  .flatMap(i -> Observable.just(i)
                                          .subscribeOn(pooledScheduler)
                                          .map(SchedulerDemo::importantLongTask)
                  )
                  .doOnTerminate(WAIT_LATCH::countDown)
                  .map(Objects::toString)
                  .subscribe(e -> log("subscribe", e));

        WAIT_LATCH.await();
        executor.shutdown();
    }

    private static int importantLongTask(int i) {
        try {
            long minMillis = 10L;
            long maxMillis = 1000L;
            log("Working on " + i);
            final long waitingTime = (long) (minMillis + (Math.random() * maxMillis - minMillis));
            Thread.sleep(waitingTime);
            return i;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void log(Object data) {
        System.out.println(Thread.currentThread().getName() + ": " + data);
    }

    public static void log(String stage, Object data) {
        System.out.println(stage + ": " + Thread.currentThread().getName() + ": " + data);
    }
}
