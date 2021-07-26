package lambda_stream;

public class LambdaCustomInterface {

    public static void main(String[] args) {
        // example of passing multiple values to a method using lambda
        // notice that I do NOT have to specify the data type of a and b
        Calculator add = (a, b) -> a + b;
        Calculator difference = (a, b) -> Math.abs(a - b);
        Calculator divide = (a, b) -> b != 0 ? a / b : 0;
        Calculator multiply = (c, d) -> c * d;

        System.out.println(add.calculate(3, 2));
        System.out.println(difference.calculate(5, 10));
        System.out.println(divide.calculate(5, 0));
        System.out.println(multiply.calculate(5, 3));
    }

    @FunctionalInterface
    private interface Calculator {
        int calculate(int x, int y);
    }
}
