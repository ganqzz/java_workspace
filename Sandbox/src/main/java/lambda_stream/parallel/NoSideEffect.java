package lambda_stream.parallel;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NoSideEffect {

    private static final List<String> members =
            Arrays.asList("A", "B", "C", "D", "E");

    private static final Function<String, String> greet =
            member -> String.format("%s: Hello, %s !", Instant.now(), member);

    public static void main(String[] args) {
        List<String> greets = members.parallelStream()
                                     .map(greet)
                                     .collect(Collectors.toList());

        greets.forEach(System.out::println);
    }
}
