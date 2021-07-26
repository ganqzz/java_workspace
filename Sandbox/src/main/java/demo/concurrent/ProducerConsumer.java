package demo.concurrent;

public class ProducerConsumer {

    private static final Object lock = new Object();
    private static final int[] buffer = new int[10]; // shared object
    private static int count = 0; // shared object

    private static class Producer {
        void produce() {
            synchronized (lock) {
                if (count == buffer.length) { // full
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count++] = 1;
                lock.notify();
            }
        }
    }

    private static class Consumer {
        void consume() {
            synchronized (lock) {
                if (count == 0) { // empty
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[--count] = 0; // Stack
                lock.notify();
            }
        }
    }

    // main
    public static void main(String... strings) throws InterruptedException {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = () -> {
            for (int i = 0; i < 50; i++) {
                producer.produce();
            }
            System.out.println("Done producing");
        };
        Runnable consumeTask = () -> {
            for (int i = 0; i < 45; i++) { // producerより大きくするとwaitingのままになる
                consumer.consume();
            }
            System.out.println("Done consuming");
        };

        Thread consumerThread = new Thread(consumeTask);
        Thread producerThread = new Thread(produceTask);

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("Data in the buffer: " + count);
    }
}
