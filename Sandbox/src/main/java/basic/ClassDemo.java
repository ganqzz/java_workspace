package basic;

interface Hogerable {
    int I = 1000;

    void hoge();

    void fuga();

}

class Oya {
    public int i = 5;
    public String str;
    private byte b = 2;

    Oya() {
        System.out.println("Oya()");
        str = "Oya";
    }

    Oya(String s) {
        str = s;
        System.out.println("Oya(s): " + s);
    }

    protected void hoge() {
        System.out.println("Oya#hoge: " + i);
    }

    protected int getIo() {
        return i;
    }

    protected int getI() {
        return i;
    }
}

class Ko extends Oya {
    public int i = 10;
    public String str;

    Ko() {
        System.out.println("Ko()");
        str = "Ko";
    }

    Ko(String s) {
        //super(s); // 明示しないと、super()の方が呼ばれる。
        System.out.println("Ko(s): " + s);
    }

    void fuga() {
        //System.out.println(super.b); // エラー
    }

    protected void hoge() {
        System.out.println("Ko#hoge: " + i);
    }

    protected int getI() {
        return i;
    }
}

class Mago extends Ko implements Hogerable {

    //protected void hoge() {
    //    System.out.println("Mago#hoge" + i);
    //}

    public static final int I = 20; // 上書き可能

    public void hoge() {}

    public void fuga() {}

}

class ClassDemo {
    public static void main(String[] args) {

        System.out.println("Oya oya = new Oya();");
        Oya oya = new Oya();
        System.out.println(oya.getI());
        System.out.println(oya.i);
        System.out.println();

        System.out.println("Ko ko = new Ko();");
        Ko ko = new Ko();
        System.out.println(ko.getI()); // => 10
        System.out.println(ko.getIo()); // => 5 ←※
        System.out.println(ko.i); // => 10
        System.out.println();

        ko = new Ko("Qwerty");
        System.out.println();

        System.out.println("Oya ko1 = new Ko();");
        Oya ko1 = new Ko();
        System.out.println(ko1.getI()); // => 10
        System.out.println(ko1.i); // => 5 ←※
        //ko1.fuga(); // エラー
        System.out.println();

        System.out.println("Ko ko2 = ko1;");
        Ko ko2 = (Ko) ko1;
        System.out.println(ko2.getI()); // => 10
        System.out.println(ko2.i); // => 10 ←※
        ko2.fuga(); // 見える
        System.out.println();

        /* フィールドは、作られた時点で別々のもの */

        System.out.println(Hogerable.I);
        System.out.println(Mago.I);
    }
}
