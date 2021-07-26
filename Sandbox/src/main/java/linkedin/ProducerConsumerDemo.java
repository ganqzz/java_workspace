package linkedin;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Producers serving soup for Consumers to eat
 */
public class ProducerConsumerDemo {

    private static class SoupProducer extends Thread {

        private BlockingQueue<String> servingLine;

        SoupProducer(BlockingQueue<String> servingLine) {
            this.servingLine = servingLine;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) { // serve 20 bowls of soup
                try {
                    //servingLine.add("Bowl #" + i);
                    servingLine.put("Bowl #" + i);
                    System.out.format("Served Bowl #%d - remaining capacity: %d\n", i,
                                      servingLine.remainingCapacity());
                    Thread.sleep(200); // time to serve a bowl of soup
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 終了通知
            //servingLine.add("no soup for you!");
            //servingLine.add("no soup for you!");
            try {
                servingLine.put("no soup for you!");
                servingLine.put("no soup for you!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class SoupConsumer extends Thread {

        private BlockingQueue<String> servingLine;

        SoupConsumer(BlockingQueue<String> servingLine) {
            this.servingLine = servingLine;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //String bowl = servingLine.remove();
                    String bowl = servingLine.take();
                    if (bowl.equals("no soup for you!")) break;

                    System.out.format("Ate %s\n", bowl);
                    Thread.sleep(300); // time to eat a bowl of soup
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> servingLine = new ArrayBlockingQueue<>(5);
        new SoupConsumer(servingLine).start();
        //new SoupConsumer(servingLine).start();
        new SoupProducer(servingLine).start();
    }
}
