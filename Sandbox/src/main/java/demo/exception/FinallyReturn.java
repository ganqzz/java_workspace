package demo.exception;

class FinallyReturn {
    public static void main(String[] args) {
        try {
            System.out.println("Before a");
            System.out.println(a(5));
            System.out.println("After a");
        } catch (Exception e) {
            System.out.println("main: " + e);
        } finally {
            System.out.println("main: finally");
        }
    }

    public static int a(int d) {
        try {
            System.out.println("a: return");
            int i = 10 / d;
            return i;
        } finally {
            System.out.println("a: finally");
        }
    }
}
