package etc;

public class Pyramid {

    // recursive
    public static void pyramid(int count) {
        if (count > 1) {
            pyramid(count - 1);
        }
//		final char[] text = new char[count];
//		java.util.Arrays.fill(text, '*');
//		System.out.println(text);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            sb.append("*");
        }
        System.out.println(sb);
    }

    // nested loop
    public static void pyramidFor(int count) {
        for (int i = 1; i <= count; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= i; j++) {
                sb.append("*");
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        pyramid(5);
        pyramidFor(5);
        // pyramid(10000); // StackOverflowError
    }
}
