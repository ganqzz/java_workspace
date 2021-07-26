package lambda_stream.design_pattern.factory;

import lambda_stream.model.Circle;

import java.awt.Color;
import java.util.List;

public class FactoryDemo {
    public static void main(String[] args) {
        Factory<Circle> factory = Factory.createFactory(Circle::new);
        Factory<Circle> factoryParams = Factory.createFactory(Circle::new, Color.RED);

        Circle circle = factory.newInstance();
        System.out.println("Circle = " + circle);

        Circle circle2 = factory.newInstance();
        System.out.println("Singleton? " + (circle == circle2));

        List<Circle> circles = factory.create5();
        System.out.println("List = " + circles);
    }
}
