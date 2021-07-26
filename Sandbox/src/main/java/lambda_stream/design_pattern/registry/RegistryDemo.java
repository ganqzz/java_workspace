package lambda_stream.design_pattern.registry;

import lambda_stream.design_pattern.factory.Factory;
import lambda_stream.design_pattern.registry.model.Rectangle;
import lambda_stream.design_pattern.registry.model.Shape;
import lambda_stream.design_pattern.registry.model.Square;
import lambda_stream.design_pattern.registry.model.Triangle;

import java.util.function.Consumer;

public class RegistryDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Consumer<Builder<Shape>> consumer1 = builder ->
                builder.register("rectangle", Rectangle::new);
        Consumer<Builder<Shape>> consumer2 = builder ->
                builder.register("triangle", Triangle::new);

        Consumer<Builder<Shape>> initializer = consumer1.andThen(consumer2);

        //Registry<Shape> registry = Registry.createRegistry(initializer);
        Registry<Shape> registry = Registry.createRegistry(
                initializer,
                s -> { throw new IllegalArgumentException("Unknown shape " + s);}
        );

        Factory<Rectangle> rectangleFactory = (Factory<Rectangle>) registry
                .buildShapeFactory("rectangle");
        Rectangle rectangle = rectangleFactory.newInstance();
        System.out.println("Rectangle = " + rectangle);

        Factory<Triangle> triangleFactory = (Factory<Triangle>) registry
                .buildShapeFactory("triangle");
        Triangle triangle = triangleFactory.newInstance();
        System.out.println("Triangle = " + triangle);

        // not registered
        Factory<Square> squareFactory = (Factory<Square>) registry.buildShapeFactory("square");
        Square square = squareFactory.newInstance();
    }
}
