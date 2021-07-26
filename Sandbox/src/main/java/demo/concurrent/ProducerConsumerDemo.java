package demo.concurrent;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Producer-Consumer pattern, using wait() and notify()
 */
public class ProducerConsumerDemo {

    private static class Producer extends Thread {
        private final String name;
        private final Queue queue;
        private final int start;
        private final int end;

        Producer(String name, Queue queue, int start) {
            super(name);
            this.name = name;
            this.queue = queue;
            this.start = start;
            this.end = start + 10;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                try {
                    sleep(1);
                } catch (InterruptedException ignore) {
                    System.out.println(getName() + ": interrupted in sleeping");
                    break;
                }

                queue.add(i);
                System.out.println(name + ": " + i);
            }

            System.out.println(name + ": END");
        }
    }

    private static class Consumer extends Thread {
        private final String name;
        private final Queue queue;
        private volatile boolean alive = true;

        Consumer(String name, Queue queue) {
            super(name);
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (alive) {
                try {
                    sleep(2);
                } catch (InterruptedException ignore) {
                    System.out.println(getName() + ": interrupted in sleeping");
                    break;
                }

                int ret = queue.remove();
                if (ret < 0) continue;

                System.out.println(name + ": " + ret);
            }

            System.out.println(name + ": END");
        }

        void quit() {
            interrupt(); // WAITINGを終了する
            alive = false;
        }
    }

    private static class Queue {
        private final static int SIZE = 10;
        private final int[] buffer = new int[SIZE]; // 循環バッファ
        private int count = 0; // waiting
        private int rp = 0;
        private int wp = 0;
        private int totalProduced = 0;
        private int totalConsumed = 0;

        synchronized void add(int i) {
            // 待ち行列がいっぱいの場合は待機する
            while (count == SIZE) {
                System.out.println("* Queue is FULL: " + Thread.currentThread().getName());
                try {
                    wait();
                } catch (InterruptedException ie) {
                    System.out.println(Thread.currentThread().getName()
                                       + ": interrupted in waiting");
                    return;
                }
            }

            // バッファにデータを追加して書き込みポインタを更新する
            buffer[wp++] = i;
            if (wp == SIZE) wp = 0;

            // countカウントを1つ増やす
            ++count;

            // total
            ++totalProduced;

            // 待機中の全スレッドに通知する
            notifyAll();
        }

        synchronized int remove() {
            // 待ち行列が空の場合は待機する
            while (count == 0) { // ifではなくwhile: 「見せかけの起動」対応
                System.out.println("* Queue is EMPTY: " + Thread.currentThread().getName());
                try {
                    wait();
                } catch (InterruptedException ie) {
                    System.out.println(Thread.currentThread().getName()
                                       + ": interrupted in waiting");
                    return Integer.MIN_VALUE;
                }
            }

            // バッファからデータを読み取って読み取りポインタを更新する
            int element = buffer[rp++];
            if (rp == SIZE) rp = 0;

            // countを1つ減らす
            --count;

            // total
            ++totalConsumed;

            // 待機中の全スレッドに通知する
            notifyAll();

            // データを返す
            return element;
        }

        synchronized void displayTotal() {
            System.out.println("\nTotal: produced = " + totalProduced
                               + ", consumed = " + totalConsumed);
        }

        synchronized boolean isEmpty() {
            // ここも同期である必要がある ※
            return count == 0;
        }
    }

    public static void main(String[] args) {
        final Queue queue = new Queue(); // monitorも兼ねる

        List<Producer> producers = IntStream
                .range(0, 5)
                .mapToObj(i -> new Producer("Producer" + (i + 1), queue, i * 10))
                .collect(Collectors.toList());

        List<Consumer> consumers = IntStream
                .range(0, 5)
                .mapToObj(i -> new Consumer("Consumer" + (i + 1), queue))
                .collect(Collectors.toList());

        Stream.concat(producers.stream(), consumers.stream())
              .forEach(Thread::start);

        // Producerが全完了するのを待つ
        producers.forEach(ProducerConsumerDemo::joinThread);

        while (true) {
            if (queue.isEmpty()) { // ※ここ
                // Consumerを終了する
                consumers.forEach(Consumer::quit);
                break;
            }
        }

        // Consumerが全完了するのを待つ
        consumers.forEach(ProducerConsumerDemo::joinThread);

        queue.displayTotal();
    }

    private static void joinThread(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
