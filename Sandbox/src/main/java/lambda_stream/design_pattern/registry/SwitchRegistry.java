package lambda_stream.design_pattern.registry;

import lambda_stream.design_pattern.factory.Factory;
import lambda_stream.design_pattern.registry.model.Rectangle;
import lambda_stream.design_pattern.registry.model.Shape;
import lambda_stream.design_pattern.registry.model.Square;
import lambda_stream.design_pattern.registry.model.Triangle;

public class SwitchRegistry {

    public Factory<? extends Shape> buildShapeFactory(String shape) {
        switch (shape) {
            case "square":
                return Square::new;
            case "triangle":
                return Triangle::new;
            case "rectangle":
                return Rectangle::new;
            default:
                throw new IllegalArgumentException("Unknown shape " + shape);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SwitchRegistry registry = new SwitchRegistry();
        Factory<Rectangle> rectangleFactory = (Factory<Rectangle>) registry
                .buildShapeFactory("rectangle");
        System.out.println("Rectangle: " + rectangleFactory.newInstance());
    }
}
