package demo.enums;

/**
 * Enum Singleton
 * 簡単かつ確実なSingletonパターン
 */
public class SingletonEnumDemo {

    public enum SingletonEnum {

        INSTANCE;

        public int plus(int x, int y) {
            return x + y;
        }

        public int minus(int x, int y) {
            return x - y;
        }
    }

    public static void main(String[] args) {
        SingletonEnum s1 = SingletonEnum.INSTANCE;
        SingletonEnum s2 = SingletonEnum.INSTANCE;
        System.out.println(s1 == s2); //=> true
    }
}
