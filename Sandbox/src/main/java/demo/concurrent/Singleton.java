package demo.concurrent;

/**
 * Double-check, Thread-safe and parallel read Singleton pattern
 */
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {} // 他クラスからのnewの禁止

    public static Singleton getInstance() {
        if (instance == null) { // volatileによるvisibilityの保証
            synchronized (Singleton.class) {
                if (instance == null) { // synchronousでない場合、ここ（read）と
                    instance = new Singleton(); // ここ（write）の間で割り込まれる可能性がある
                }
            }
        }
        return instance;
    }
}
