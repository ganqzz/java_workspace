package demo.concurrent.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        // 成功
        log("success-1");
        CompletableFuture<String> successCF = CompletableFuture.supplyAsync(() -> {
            sleep(3_000L);
            return "success";
        });
        try {
            log("success-2");
            log("success-3: result=%s", successCF.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 失敗
        log("failure-1");
        CompletableFuture<Void> failureCF = CompletableFuture.runAsync(() -> {
            sleep(3_000L);
            throw new RuntimeException("failure");
        });
        try {
            log("failure-2");
            failureCF.get();
        } catch (InterruptedException | ExecutionException e) {
            log("failure-3: exception=%s", e);
        }

        // キャンセル
        log("cancel-1");
        CompletableFuture<Void> cancelCF = CompletableFuture.runAsync(() -> {
            sleep(3_000L);
        });
        log("cancel-2");
        sleep(1_500L);
        log("cancel-3: %s", cancelCF.cancel(true));

        // 合成 (Either, async)
        CompletableFuture<Long> leftCF = CompletableFuture.supplyAsync(() -> {
            log("either-left: start");
            sleep(2_000L);
            long t = System.currentTimeMillis();
            log("either-left: time=%d", t);
            return t;
        });
        CompletableFuture<Long> rightCF = CompletableFuture.supplyAsync(() -> {
            log("either-right: start");
            sleep(3_000L);
            long v = 123L;
            log("either-right: value=%d", v);
            return v;
        });
        CompletableFuture<Void> eitherCF = leftCF.acceptEitherAsync(rightCF, x -> {
            log("either-result: x=%d", x);
        });
        try {
            eitherCF.get();
            log("either-end");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void log(String fmt, Object... args) {
        String now = String.format("%1$tT.%1$tL", System.currentTimeMillis());
        now = "--:" + now.substring(3);
        System.out.printf("%s [%s] %s%n", now, Thread.currentThread().getName(),
                          String.format(fmt, args));
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
