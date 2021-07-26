package basic;

import java.io.IOException;

public class MyAutoCloseable implements AutoCloseable {

    public void saySomething() throws IOException {
        System.out.println("Something");
        throw new IOException("Exception from saySomething");
    }

    @Override
    public void close() throws IOException {
        System.out.println("close");
        throw new IOException("Exception from close");
    }

    // main()
    public static void main(String... args) {
        try (MyAutoCloseable ac = new MyAutoCloseable()) {
            ac.saySomething();
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());

            for (Throwable t : e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        }
    }
}
