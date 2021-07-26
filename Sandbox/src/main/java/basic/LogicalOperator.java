package basic;

class LogicalOperator {
    public static void main(String[] args) {
        // boolean i = Boolean.valueOf(args[0]).booleanValue();
        // boolean j = Boolean.valueOf(args[1]).booleanValue();

        printResult(true, true);
        printResult(true, false);
        printResult(false, true);
        printResult(false, false);
    }

    static void printResult(boolean i, boolean j) {
        System.out.println("\r\n---");
        System.out.println("i = " + i + ", j = " + j);
        System.out.println("  i & j " + (i & j));
        System.out.println("  i | j " + (i | j));
        System.out.println("  i ^ j " + (i ^ j));
        System.out.println("  !i " + !i);
        System.out.println("  i && j " + (i && j));
        System.out.println("  i || j " + (i || j));
        System.out.println("  i == j " + (i == j));
        System.out.println("  i != j " + (i != j));
    }
}
