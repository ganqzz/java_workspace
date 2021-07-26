package lambda_stream.parallel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SideEffectDemo {

    private static final List<String> members =
            Arrays.asList("A", "B", "C", "D", "E");

    private static final List<String> greets = new ArrayList<>();

    private static final Consumer<String> greet = member -> {
        int n = greets.size() + 1;
        greets.add(String.format("%d番目: %s: Hello, %s !", n, Instant.now(), member));
    };

    public static void main(String[] args) {
        members.parallelStream().forEach(greet);
        greets.forEach(System.out::println);
    }
}
