package com.example.demo;

/**
 * Threads that waste CPU cycles
 */
public class ThreadProcessDemo {
    public static void main(String[] args) throws InterruptedException {

        // display current information about this process
        Runtime rt = Runtime.getRuntime();
        long usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);

        // a simple task wastes CPU cycles forever
        Runnable cpuWaster = () -> {
            while (true) {}
        };

        // start 6 new threads
        System.out.println("\nStarting 6 CPUWaster threads...\n");
        for (int i = 0; i < 6; i++) {
            new Thread(cpuWaster).start();
        }

        // display current information about this process
        usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);
    }
}
