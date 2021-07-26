package lambda_stream;

import lambda_stream.model.Person;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FunctionExample {

    public static void main(String[] args) {
        // using the test method of Predicate
        Predicate<String> stringLen = s -> s.length() < 10;
        System.out.println(stringLen.test("Apples") + " - Apples is less than 10");

        // Consumer example uses accept method
        Consumer<String> consumerStr = s -> System.out.println(s.toLowerCase());
        consumerStr.accept("ABCDefghijklmnopQRSTuvWxyZ");

        // Supplier example
        Supplier<String> s = () -> "Java is fun";
        System.out.println(s.get());

        Supplier<Person> s2 = Person::new; // factory

        // Function example
        Function<Integer, String> converter = num -> Integer.toString(num);
        System.out.println("length of 26: " + converter.apply(26).length());

        // BiFunction example
        BiFunction<String, String, Integer> concat = (a, b) -> a.length() + b.length();
        System.out.println(concat.apply("Hello", "あわわ"));

        // Unary Operator example
        //UnaryOperator<String> str = (msg) -> msg.toUpperCase();
        UnaryOperator<String> str = String::toUpperCase;
        System.out.println(str.apply("This is my message in upper case"));

        // Binary Operator example
        //BinaryOperator<Integer> add = (a, b) -> a + b;
        BinaryOperator<Integer> add = Integer::sum;
        System.out.println("add 10 + 25: " + add.apply(10, 25));
    }
}
