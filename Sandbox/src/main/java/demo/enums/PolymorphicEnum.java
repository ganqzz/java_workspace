package demo.enums;

public enum PolymorphicEnum {

    PLUS {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },

    MINUS {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    };

    public abstract double apply(double x, double y);

    public static void main(String[] args) {
        System.out.println(PolymorphicEnum.PLUS.apply(1, 2));
        System.out.println(PolymorphicEnum.MINUS.apply(1, 2));
    }
}
