package lambda_stream.parallel;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ParallelTest {

    private static final List<String> members =
            Arrays.asList("A", "B", "C", "D", "E");

    private static final Consumer<String> greet = member -> {
        System.out.println(String.format(
                "%s: Hello, %s !", Instant.now(), member));
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignore) {
        }
        System.out.println(String.format(
                "%s: Good-bye, %s !", Instant.now(), member));
    };

    public static void main(String[] args) {
        // sequential
        members.stream().forEach(greet);

        System.out.println("---");

        // parallel
        members.parallelStream().forEach(greet);
    }
}
