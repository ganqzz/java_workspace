package basic;

public class VarArgs {

    public static void main(String[] args) {
        System.out.println(calcAvgMultiValues(1, 2, 3, 4, 5, 6));
        System.out.println(calcAvgMultiValues(new double[]{1, 2, 3, 4, 5, 6}));
    }

    private static double calcAvgMultiValues(double... values) {
        double result = 0;
        for (double d : values)
            result += d;
        return result / values.length;
    }

}
