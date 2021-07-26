package basic;

class NaN {
    public static void main(String[] args) {
        float x = 0 / 0f; // NaN
        float y = 1 / 0f; // Infinity
        float z = -1 / 0f; // -Infinity

        System.out.println("x = " + x);
        System.out.println("x == x : " + (x == x ? "等しい" : "等しくない"));
        System.out.println("x.isNaN() : " + new Float(x).isNaN());

        System.out.println();
        System.out.println("y = " + y);
        System.out.println("z = " + z);
        System.out.println("y == y : " + (y == y ? "等しい" : "等しくない"));
        System.out.println("y == z : " + (y == z ? "等しい" : "等しくない"));
        System.out.println("y.isNaN() : " + new Float(y).isNaN());
        System.out.println("y.isInfinite() : " + new Float(y).isInfinite());
    }
}
