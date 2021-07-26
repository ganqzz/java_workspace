package lambda_stream.design_pattern.visitor;

import lambda_stream.design_pattern.visitor.model.Body;
import lambda_stream.design_pattern.visitor.model.Car;
import lambda_stream.design_pattern.visitor.model.Engine;

import java.util.function.Consumer;

public class VisitorDemo {
    public static void main(String[] args) {
        Car car = new Car();
        Engine engine = new Engine();
        Body body = new Body();

        // Visitor: element(acceptor)を変更せずに処理を追加・変更する
        Consumer<VisitorBuilder<String>> consumer =
                Visitor.<Car, String>forType(Car.class)
                        .execute((Car c) -> "Visiting car: " + c)
                        .forType(Engine.class)
                        .execute((Engine e) -> "Visiting engine: " + e)
                        .forType(Body.class)
                        .execute((Body b) -> "Visiting body: " + b);

        Visitor<String> visitor = Visitor.of(consumer);

        String visitedEngine = visitor.visit(engine);
        System.out.println("Engine: " + visitedEngine);

        String visitedCar = visitor.visit(car);
        System.out.println("Car: " + visitedCar);

        String visitedBody = visitor.visit(body);
        System.out.println("Body: " + visitedBody);
    }
}
