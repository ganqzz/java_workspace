package lambda_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Java8InterfaceDemo {
    public static void main(String... args) {
        List<IStrategy> strategies = new ArrayList<>();
        strategies.add(() -> "hoge");
        strategies.add(() -> "fuga");
        strategies.add(() -> "fefe");

        Predicate<IStrategy> pred = p -> p.get().contains("e");

        strategies.stream()
                  .filter(pred)
                  .map(s -> s.getInfo()) // default method
                  .forEach(System.out::println);

        strategies.stream()
                  .filter(pred)
                  .map(s -> IStrategy.getInfo(s)) // static method
                  .forEach(System.out::println);
    }

    private interface IStrategy {

        String get();

        default String getInfo() {
            return this.get();
        }

        static String getInfo(IStrategy i) {
            return i.get();
        }
    }
}
