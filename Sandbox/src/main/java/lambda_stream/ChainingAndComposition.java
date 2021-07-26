package lambda_stream;

import java.util.Objects;

public class ChainingAndComposition {

    @FunctionalInterface
    private interface Function<T, R> {
        R apply(T t);

        // chaining
        // Predicate, Consumerでも可能
        default <V> Function<T, V> andThen(Function<R, V> other) {
            Objects.requireNonNull(other);
            return (T t) -> {
                R r = this.apply(t);
                return other.apply(r);
            };
        }

        // composition
        default <V> Function<V, R> compose(Function<V, T> other) {
            Objects.requireNonNull(other);
            return (V v) -> {
                T t = other.apply(v);
                return this.apply(t);
            };
        }
    }

    public static void main(String[] args) {
        Function<Float, Integer> f1 = Float::intValue;
        Function<Integer, String> f2 = Integer::toBinaryString;

        Function<Float, String> f3 = f1.andThen(f2);
        Function<Float, String> f4 = f2.compose(f1);

        System.out.println(f3.apply(5.4f));
        System.out.println(f4.apply(5.4f));
    }
}
