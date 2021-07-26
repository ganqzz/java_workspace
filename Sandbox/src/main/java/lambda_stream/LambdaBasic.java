package lambda_stream;

import java.util.Comparator;
import java.util.concurrent.Callable;

public class LambdaBasic {

    private String field = "Enclosing class";

    private Runnable anon = new Runnable() {
        String field = "Inner class";

        @Override
        public void run() {
            System.out.println("anon: this.field = " + this.field);
            System.out.println("anon: Enclosing.this.field = " + LambdaBasic.this.field);
        }
    };

    private Runnable lambda = () -> {
        System.out.println("lambda: this.field = " + this.field);
    };

    public static void main(String[] args) {
        // "this" demo
        LambdaBasic demo = new LambdaBasic();
        demo.anon.run();
        demo.lambda.run();

        System.out.println("---");

        Runnable r1 = () -> System.out.println("Running Thread 1");
        Runnable r2 = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Running Thread 2");
        };

        new Thread(r1).start();
        new Thread(r2).start();

        // another FunctionalInterfaces prior to Java8
        Callable<String> c = () -> "Callable!"; // similar to Supplier but throwing Exception

        Comparator<String> cmp = Comparator.comparing(String::toUpperCase);
    }
}
