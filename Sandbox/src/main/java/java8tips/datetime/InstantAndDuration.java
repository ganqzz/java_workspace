package java8tips.datetime;

import java.time.Duration;
import java.time.Instant;

public class InstantAndDuration {

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        System.out.println(start);

        Thread.sleep(1000);

        Instant end = Instant.now(); // immutable
        System.out.println(end);

        Duration elapsed = Duration.between(start, end);

        System.out.println("Elapsed: " + elapsed.toMillis() + " milliseconds");
    }
}
