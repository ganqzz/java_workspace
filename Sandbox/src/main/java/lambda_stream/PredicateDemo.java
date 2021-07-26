package lambda_stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {

    private static final List<String> strings =
            Arrays.asList("one", "two", "three", "four", "five");

    public static void main(String[] args) {

        Predicate<String> p1 = s -> s.length() > 3;
        display(p1);

        // static factory method
        Predicate<String> p2 = Predicate.isEqual("two");
        Predicate<String> p3 = Predicate.isEqual("three");

        // default methods
        Predicate<String> p4 = p1.and(p3);
        Predicate<String> p5 = p2.or(p3);
        Predicate<String> p6 = p1.negate();

        display(p2);
        display(p3);
        display(p4);
        display(p5);
        display(p6);
    }

    private static void display(Predicate<? super String> predicate) {
        List<String> results = strings.stream()
                                      .filter(predicate)
                                      .collect(Collectors.toList());
        System.out.println(results);
    }
}
