package demo.concurrent;

import java.util.concurrent.TimeUnit;

public class SleepDemo {
    public static void main(String[] args) throws InterruptedException {
        int val = 1000;

        System.out.println("--- Thread.sleep()");

        System.out.println("Sleeping for " + val + "ms");
        Thread.sleep(val);

        System.out.println("Sleeping for " + val + "ms + " + val + "ns");
        Thread.sleep(val, val);

        System.out.println("--- TimeUnit.sleep()");

        System.out.println("Sleeping for " + val + "ms");
        TimeUnit.MILLISECONDS.sleep(val);

        System.out.println("Sleeping for " + val + "us");
        TimeUnit.MICROSECONDS.sleep(val);

        System.out.println("Done");
    }
}
