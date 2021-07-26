package lambda_stream.design_pattern.visitor;

import java.util.function.Function;

public interface VisitorBuilder<R> {

    // このメソッドのみの型パラメータ
    // not Functional Interface any more
    <T> void register(Class<T> type, Function<T, R> function);
}
