package basic;

class Super {
    public static void hoge() {
        System.out.println("Super.hoge()");
    }

    public Super() {
        System.out.println("Super()");
        hoge();
    }

    public Super(String str) {
        System.out.println("Super(" + str + ")");
        hoge();
    }

    private void method() {}
}

class Sub extends Super {
    public static void hoge() {
        System.out.println("Sub.hoge()");
    }

    public Sub() {
        System.out.println("Sub()");
    }

    public Sub(String str) {
        System.out.println("Sub(" + str + ")");
    }

    // オーバーライドではなく、別メソッド
    protected void method() {}
}

public class SuperDemo {
    public static void main(String[] args) {
        new Sub();
        System.out.println();
        new Sub("Hoge");
    }
}
