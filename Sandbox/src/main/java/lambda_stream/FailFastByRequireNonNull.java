package lambda_stream;

import java.util.Objects;

public class FailFastByRequireNonNull {

    @FunctionalInterface
    private interface ConsumerBad<T> {
        void accept(T t);

        default ConsumerBad<T> andThen(ConsumerBad<T> other) {
            return (T t) -> {
                this.accept(t);
                other.accept(t);
            };
        }
    }

    @FunctionalInterface
    private interface ConsumerGood<T> {
        void accept(T t);

        default ConsumerGood<T> andThen(ConsumerGood<T> other) {
            Objects.requireNonNull(other);
            return (T t) -> {
                this.accept(t);
                other.accept(t);
            };
        }
    }

    private static void noFailFast() {
        ConsumerBad<String> c1 = s -> System.out.println("c1 = " + s);
        ConsumerBad<String> c2 = s -> System.out.println("c2 = " + s);

        ConsumerBad<String> c3 = c1.andThen(null);

        c3.accept("Hello");

        System.out.println("End");
    }

    private static void failFast() {
        ConsumerGood<String> c1 = s -> System.out.println("c1 = " + s);
        ConsumerGood<String> c2 = s -> System.out.println("c2 = " + s);

        ConsumerGood<String> c3 = c1.andThen(null); // ここでストップする

        c3.accept("Hello");

        System.out.println("End");
    }

    public static void main(String[] args) {
        //noFailFast();
        failFast();
    }
}
