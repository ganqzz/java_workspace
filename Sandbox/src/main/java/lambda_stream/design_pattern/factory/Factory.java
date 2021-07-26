package lambda_stream.design_pattern.factory;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Factory<T> extends Supplier<T> {

    // 名前変えただけ
    default T newInstance() {
        return get();
    }

    default List<T> create5() {
        return IntStream.range(0, 5)
                        .mapToObj(i -> newInstance())
                        .collect(Collectors.toList());
    }

    // Generic Factory
    static <T> Factory<T> createFactory(Supplier<T> supplier) {
        T singleton = supplier.get();
        return () -> singleton; // 同じFactoryからは、同じインスタンスを返す = Singleton
    }

    // Generic Factory with parameters
    // 複数の引数がある場合も、畳込めば最終的には1個にすることができる（入れ子 or reduce）
    static <T, P> Factory<T> createFactory(Function<P, T> constructor, P param) {
        // partial application
        return () -> constructor.apply(param);
    }
}
