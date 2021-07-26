package lambda_stream;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeDemo {
    public static void main(String[] args) {
        System.out.println(
                IntStream.range(0, 5)
                         .mapToObj(String::valueOf)
                         .collect(Collectors.joining(", ", "[", "]"))
        );
        System.out.println(
                IntStream.rangeClosed(0, 5)
                         .mapToObj(String::valueOf)
                         .collect(Collectors.joining(", ", "[", "]"))
        );

        System.out.println("---");
        IntStream.range(0, 0)
                 .average()
                 .ifPresent(System.out::println);

        System.out.println("---");
        System.out.println(
                IntStream.range(0, 0)
                         .average()
                         .orElse(-1)
        );

        System.out.println("---");
        IntStream.iterate(1, i -> i * 2)
                 .limit(10)
                 .forEach(System.out::println);
    }
}
