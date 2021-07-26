package lambda_stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class FlatMapExample {

    public static void main(String... args) {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> list2 = Arrays.asList(2, 4, 6);
        List<Integer> list3 = Arrays.asList(3, 5, 7);

        List<List<Integer>> list = Arrays.asList(list1, list2, list3);

        System.out.println(list);

        Function<List<?>, Integer> size = List::size;
        list.stream()
            .map(size)
            .forEach(System.out::println);

        System.out.println();

        Function<List<Integer>, Stream<Integer>> flatMapper = Collection::stream;
        list.stream()
            .flatMap(flatMapper)
            .forEach(System.out::println);

        System.out.println();

        List<String> nameList = Arrays.asList("Tanaka", "Suzuki", "Takahashi");
        // String, Integer
        Stream<Object> stream = nameList.stream().flatMap(x -> Stream.of(x, x.length()));
        stream.forEach(System.out::println);
    }
}
