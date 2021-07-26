package demo.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheNotThreadSafe {

    private Map<Long, String> cache = new HashMap<>(); // non thread safe

    public String put(Long key, String value) {
        return cache.put(key, value);
    }

    public String get(Long key) {
        return cache.get(key);
    }

    public static void main(String[] args) {

        CacheNotThreadSafe cache = new CacheNotThreadSafe();

        class Producer implements Callable<String> {

            private Random rand = new Random();

            @Override
            public String call() throws Exception {
                while (true) {
                    long key = rand.nextLong();
                    cache.put(key, Long.toString(key));
                    Thread.sleep(1); // ウェイトを入れる
                    if (cache.get(key) == null) { // null: race condition
                        System.out.println(key + " is missing!");
                    }
                }
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        System.out.println("Adding value...");

        try {
            for (int i = 0; i < 4; i++) {
                executorService.submit(new Producer());
            }
        } finally {
            executorService.shutdown();
        }
    }
}
