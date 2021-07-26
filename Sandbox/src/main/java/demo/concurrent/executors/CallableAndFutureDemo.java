package demo.concurrent.executors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ExecutorService with Callable and Future
 * Return value
 */
public class CallableAndFutureDemo {

    private static class Counter implements Callable<Integer> {

        private String inFile;

        public Counter(String inFile) {
            this.inFile = inFile;
        }

        @Override
        public Integer call() throws IOException {
            int total = 0;

            //try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
                while (reader.readLine() != null) {
                    total++;
                }
            } // fileが見つからない場合は、skipする

            return total;
        }
    }

    public static void main(String[] args) {
        String[] inFiles = {"files/loremipsum.txt", "files/hamlet.txt",
                "_notfound_.txt", "temp/todo.txt"};
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer>[] results = new Future[inFiles.length];

        for (int i = 0; i < inFiles.length; i++) {
            Counter counter = new Counter(inFiles[i]);
            results[i] = es.submit(counter);
        }

        for (int i = 0; i < inFiles.length; i++) {
            try {
                //int value = results[i].get(); // blocks until return value available indeterminately
                int value = results[i].get(1000, TimeUnit.MILLISECONDS); // blocks until return value available or timeout
                System.out.println(inFiles[i] + ": " + value);
            } catch (ExecutionException e) { // Exception raised from the Future
                Throwable cause = e.getCause(); // Get the root exception
                System.out.println(cause.toString());
            } catch (TimeoutException e) { // Future.get(timeout)
                e.printStackTrace();
            } catch (InterruptedException e) { // Future.get(...)
                e.printStackTrace();
            } catch (Exception e) { // other exceptions
                e.printStackTrace();
            }
        }

        es.shutdown();
        System.out.println("END");
    }
}
