package linkedin.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Download a collection of images
 */
public class DownloadImagesSolution {

    /* sequential implementation of multiple image downloader */
    private static class SequentialImageDownloader {

        private int[] imageNumbers;

        SequentialImageDownloader(int[] imageNumbers) {
            this.imageNumbers = imageNumbers;
        }

        /* returns total bytes from downloading all images in imageNumbers array */
        int downloadAll() {
            int totalBytes = 0;
            for (int num : imageNumbers) {
                totalBytes += downloadImage(num);
            }
            return totalBytes;
        }
    }

    /* parallel implementation of multiple image downloader */
    private static class ParallelImageDownloader {

        private int[] imageNumbers;

        ParallelImageDownloader(int[] imageNumbers) {
            this.imageNumbers = imageNumbers;
        }

        /* returns total bytes from downloading all images in imageNumbers array */
        int downloadAll() {
            // create threadpool
            int numWorkers = Runtime.getRuntime().availableProcessors() * 5; // IOタスクなので倍数
            ExecutorService pool = Executors.newFixedThreadPool(numWorkers);

            // submit callable futures to threadpool
            List<Future<Integer>> futures = new ArrayList<>();
            for (int num : imageNumbers) {
                Callable<Integer> imageRequest = () -> downloadImage(num);
                futures.add(pool.submit(imageRequest));
            }

            // retrieve and accumulate results from futures
            int totalBytes = 0;
            try {
                for (Future<Integer> f : futures)
                    totalBytes += f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            pool.shutdown();
            return totalBytes;
        }
    }

    /* returns number of bytes from downloading image */
    private static int downloadImage(int imageNumber) {
        imageNumber = (Math.abs(imageNumber) % 50) + 1; // force number between 1 and 50
        InputStream in = null;
        try {
            URL photoURL = new URL(
                    String.format("http://699340.youcanlearnit.net/image%03d.jpg", imageNumber));
            in = photoURL.openStream();
            int totalBytes = 0;
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                totalBytes += bytesRead;
            }
            return totalBytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /* evaluate performance of sequential and parallel implementations */
    public static void main(String[] args) {
        final int NUM_EVAL_RUNS = 1;
        final int[] IMAGE_NUMBERS = IntStream.rangeClosed(1, 50).toArray(); // images to download

        System.out.println("Evaluating Sequential Implementation...");
        SequentialImageDownloader sid = new SequentialImageDownloader(IMAGE_NUMBERS);
        int sequentialResult = sid.downloadAll();
        double sequentialTime = 0;
        for (int i = 0; i < NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            sid.downloadAll();
            sequentialTime += System.currentTimeMillis() - start;
        }
        sequentialTime /= NUM_EVAL_RUNS;

        System.out.println("Evaluating Parallel Implementation...");
        ParallelImageDownloader pid = new ParallelImageDownloader(IMAGE_NUMBERS);
        int parallelResult = pid.downloadAll();
        double parallelTime = 0;
        for (int i = 0; i < NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            pid.downloadAll();
            parallelTime += System.currentTimeMillis() - start;
        }
        parallelTime /= NUM_EVAL_RUNS;

        // display sequential and parallel results for comparison
        if (sequentialResult != parallelResult) {
            throw new AssertionError("ERROR: sequentialResult and parallelResult do not match!");
        }
        System.out.format("Downloaded %d images totaling %.1f MB\n", IMAGE_NUMBERS.length,
                          sequentialResult / 1e6);
        System.out.format("Average Sequential Time: %.1f ms\n", sequentialTime);
        System.out.format("Average Parallel Time: %.1f ms\n", parallelTime);
        System.out.format("Speedup: %.2f\n", sequentialTime / parallelTime);
        System.out.format("Efficiency: %.2f%%\n", 100 * (sequentialTime / parallelTime) /
                                                  Runtime.getRuntime().availableProcessors());
    }
}
