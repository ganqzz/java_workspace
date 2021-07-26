package demo.enums;

public enum StrategyEnum {

    PLUS(Strategy.PLUS), MINUS(Strategy.MINUS);

    private final Strategy strategy;

    StrategyEnum(Strategy strategy) {
        this.strategy = strategy;
    }

    public double apply(double x, double y) {
        return strategy.apply(x, y); // delegation
    }

    // Encapsulated Strategy Enum Type
    private enum Strategy {

        PLUS {
            @Override
            double apply(double x, double y) {
                return x + y;
            }
        },

        MINUS {
            @Override
            double apply(double x, double y) {
                return x - y;
            }
        };

        abstract double apply(double x, double y);
    }

    public static void main(String[] args) {
        System.out.println(StrategyEnum.PLUS.apply(1, 2));
        System.out.println(StrategyEnum.MINUS.apply(1, 2));
    }
}
