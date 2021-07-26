package basic;

public class PrivateDemo {

    private int hoge;

    public static void main(String[] args) {
        PrivateDemo me = new PrivateDemo();
        PrivateDemo other = new PrivateDemo();
        me.hoge = 1;
        other.hoge = 2;
        System.out.println(me.equals(other));
    }

    @Override
    public boolean equals(Object obj) {
        PrivateDemo other = (PrivateDemo) obj;
        return this.hoge == other.hoge; // private でもアクセスできる。
    }
}
