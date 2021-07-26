package basic;

public class InfiniteRecursive {

    private static int loop() {
        return loop();
    }

    public static void main(String[] args) {
        loop();
    }
}
