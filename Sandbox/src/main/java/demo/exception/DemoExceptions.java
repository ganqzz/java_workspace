package demo.exception;

import java.io.IOException;

public class DemoExceptions {

    public DemoExceptions() {
        super();
    }

    public static void main(String[] args) {
        showExceptions();
    }

    private static void showExceptions() {
        System.out.println("Calling method1");
        method1();
        System.out.println("After calling method1");
    }

    private static void method1() {
        System.out.println("Calling method2");
        method2();
        System.out.println("After calling method2");
        throw new AssertionError("Throwing an Error which is unchecked");
    }

    private static void method2() {
        System.out.println("Calling method3");
        method3();
        System.out.println("After calling method3");
        throw new IllegalArgumentException("Having fun demonstrating unchecked exception");
    }

    private static void method3() {
        System.out.println("Entering try/catch block");
        try {
            System.out.println("Calling methodThrowsCheckedException");
            methodThrowsCheckedException();
            System.out.println("After calling methodThrowsCheckedException");
        } catch (IOException ioe) {
            System.out.println("IOException caught");
            ioe.printStackTrace();
        }
    }

    private static void methodThrowsCheckedException() throws IOException {
        System.out.println("About to throw IOException");
        throw new IOException("Demonstrate throwing an exception");
    }
}
