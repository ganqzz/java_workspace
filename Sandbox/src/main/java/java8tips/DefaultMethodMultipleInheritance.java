package java8tips;

public class DefaultMethodMultipleInheritance {

    interface Interface1 {
        default void defaultMethod() {
            System.out.println("Interface1");
        }
    }

    interface Interface2 {
        default void defaultMethod() {
            System.out.println("Interface2");
        }
    }

    interface Interface3 extends Interface1, Interface2 {
        // 同じ名前のdefault methodがある場合は、overrideして解決する必要がある
        @Override
        default void defaultMethod() {
            System.out.println("Interface3");
        }
    }

    private static class Hoge implements Interface3 {}

    public static void main(String[] args) {
        new Hoge().defaultMethod();
    }
}
