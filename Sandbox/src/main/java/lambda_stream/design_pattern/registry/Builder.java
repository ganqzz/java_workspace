package lambda_stream.design_pattern.registry;

import lambda_stream.design_pattern.factory.Factory;

public interface Builder<T> {

    void register(String label, Factory<T> factory);
}
