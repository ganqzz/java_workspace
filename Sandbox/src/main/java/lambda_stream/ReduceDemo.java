package lambda_stream;

import java.util.stream.IntStream;

public class ReduceDemo {
    public static void main(String[] args) {
        int[] numbers = {1, 4, 6, 9, 13, 16};

        int oddSum = IntStream.of(numbers)
                              .filter(i -> i % 2 != 0)
                              .sum();
        System.out.println(oddSum);

        IntStream.of(numbers)
                 .filter(i -> i % 2 != 0)
                 .reduce(Integer::sum)
                 .ifPresent(System.out::println);
    }
}
