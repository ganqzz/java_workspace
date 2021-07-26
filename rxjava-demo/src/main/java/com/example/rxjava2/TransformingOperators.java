package com.example.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TransformingOperators {

    public static void main(String[] args) throws Exception {
        mapDemo();
        System.out.println("---");
        mapDemo2();
        System.out.println("---");
        flatMapDemo();
        System.out.println("---");
        map_flatMapDemo();
        System.out.println("---");
        flatMap();
        concatMap();
        switchMap();
        System.out.println("---");
        flatMap_concatMap();
    }

    private static void mapDemo() {

        Flowable<String> flowable =
                Flowable.just("A", "B", "C", "D", "E")  // 引数のデータを順に通知するFlowableの生成
                        //.map(data -> data.toLowerCase()); // mapメソッドを使って小文字に変換
                        .map(String::toLowerCase); // mapメソッドを使って小文字に変換

        // 購読開始
        flowable.subscribe(new DebugSubscriber<>());
    }

    private static void flatMapDemo() throws Exception {

        Flowable<String> flowable =
                Flowable.just("A", "B", "", "", "C")  // 引数のデータを順に通知するFlowableの生成
                        // flatMapメソッドを使って空文字を除きかつ小文字に変換
                        .flatMap(data -> {
                            if (data.isEmpty()) {
                                return Flowable.empty();  // 空文字なら空のFlowableを返す
                            } else {
                                // 受け取ったデータの大文字と小文字を300ミリ秒後に通知する
                                return Flowable.just(data.toUpperCase(), data.toLowerCase())
                                               .delay(300L, TimeUnit.MILLISECONDS);
                            }
                        });

        // 購読開始
        flowable.subscribe(new DebugSubscriber<>());

        // しばらく待つ
        Thread.sleep(1000L);
    }

    private static void mapDemo2() {
        class User {
            String userId;

            User(String userId) {
                this.userId = userId;
            }
        }

        class UserCredentials {
            final User user;
            final String accessToken;

            UserCredentials(User user, String accessToken) {
                this.user = user;
                this.accessToken = accessToken;
            }
        }

        Observable.just(new User("1"), new User("2"), new User("3"))
                  .map(user -> new UserCredentials(user, "accessToken"))
                  .subscribe(credentials -> log(credentials.user.userId, credentials.accessToken));
    }

    private static void map_flatMapDemo() {
        Observable.just("ID1", "ID2", "ID3")
                  .map(id -> Observable.fromCallable(mockHttpRequest(id)))
                  .subscribe(value -> log("map-subscribe", value));

        // = flatMap
        Observable.just("ID1", "ID2", "ID3")
                  .map(id -> Observable.fromCallable(mockHttpRequest(id)))
                  .subscribe(e -> e.subscribe(value -> log("subscribe-subscribe", value)));

        Observable.just("ID1", "ID2", "ID3")
                  .flatMap(id -> Observable.fromCallable(mockHttpRequest(id))) // mapper
                  .subscribe(value -> log("flatMap-subscribe", value));
    }

    private static void flatMap() throws Exception {
        final TestScheduler scheduler = new TestScheduler();

        Observable.fromArray("a", "b", "c", "d", "e", "f")
                  .flatMap(s -> {
                      final int delay = new Random().nextInt(10);
                      return Observable.just(s + "x")
                                       .delay(delay, TimeUnit.SECONDS, scheduler);
                  })
                  .toList()
                  .subscribe(System.out::println);

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);
    }

    private static void concatMap() throws Exception {
        final TestScheduler scheduler = new TestScheduler();

        Observable.fromArray("a", "b", "c", "d", "e", "f")
                  .concatMap(s -> {
                      final int delay = new Random().nextInt(10);
                      return Observable.just(s + "x")
                                       .delay(delay, TimeUnit.SECONDS, scheduler);
                  })
                  .toList()
                  .subscribe(System.out::println);

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);
    }

    private static void switchMap() throws Exception {
        final TestScheduler scheduler = new TestScheduler();

        Observable.fromArray("a", "b", "c", "d", "e", "f")
                  .switchMap(s -> {
                      final int delay = new Random().nextInt(10);
                      return Observable.just(s + "x")
                                       .delay(delay, TimeUnit.SECONDS, scheduler);
                  })
                  .toList()
                  .subscribe(System.out::println);

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);
    }

    private static void flatMap_concatMap() throws Exception {
        final TestScheduler scheduler1 = new TestScheduler();
        final TestScheduler scheduler2 = new TestScheduler();

        Observable.fromArray("a", "b", "c", "d", "e", "f")
                  .flatMap(s -> Observable.just(s + "x")
                                          .delay(5, TimeUnit.SECONDS, scheduler1)
                                          .doOnNext(str -> System.out
                                                  .print(scheduler1.now(TimeUnit.SECONDS) + " ")))
                  .toList()
                  .subscribe(strings -> System.out
                          .println("\nEND:" + scheduler1.now(TimeUnit.SECONDS)));

        scheduler1.advanceTimeBy(1, TimeUnit.MINUTES);

        Observable.fromArray("a", "b", "c", "d", "e", "f")
                  .concatMap(s -> Observable.just(s + "x")
                                            .delay(5, TimeUnit.SECONDS, scheduler2)
                                            .doOnNext(str -> System.out
                                                    .print(scheduler2.now(TimeUnit.SECONDS) + " ")))
                  .toList()
                  .subscribe(strings -> System.out
                          .println("\nEND:" + scheduler2.now(TimeUnit.SECONDS)));

        scheduler2.advanceTimeBy(1, TimeUnit.MINUTES);
    }

    private static Callable<Date> mockHttpRequest(String id) {
        return Date::new;
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
