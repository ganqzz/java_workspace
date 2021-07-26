package demo.generics.why;

public class GenericExample {
    public static void main(String[] args) {
        CircularBuffer buffer = new CircularBuffer(10);

        buffer.offer("a");
        buffer.offer("bc");
        buffer.offer("d");

        buffer.offer(1);

        System.out.println(concatenate(buffer));

        System.out.println("---");

        GenericCircularBuffer<String> buffer1 = new GenericCircularBuffer<>(10);

        buffer1.offer("a");
        buffer1.offer("bc");
        buffer1.offer("d");

        //buffer.offer(1);

        System.out.println(concatenate(buffer1));
    }

    private static String concatenate(CircularBuffer buffer) {
        StringBuilder result = new StringBuilder();

        String value;
        while ((value = (String) buffer.poll()) != null) {
            result.append(value);
        }

        return result.toString();
    }

    private static String concatenate(GenericCircularBuffer<String> buffer) {
        StringBuilder result = new StringBuilder();

        String value;
        while ((value = buffer.poll()) != null) {
            result.append(value);
        }

        return result.toString();
    }
}
