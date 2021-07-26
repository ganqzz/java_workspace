package etc;

public class Janken {
    static final String[] HAND = {"グー", "チョキ", "パー"};

    static final String EVEN = "あいこ";
    static final String A_WIN = "Aさんの勝ち";
    static final String B_WIN = "Bさんの勝ち";

    static final String[][] MATRIX = {
            {EVEN, A_WIN, B_WIN},
            {B_WIN, EVEN, A_WIN},
            {A_WIN, B_WIN, EVEN},
    };

    // main()
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            execJanken();
    }

    // じゃんけん表示する。
    static void execJanken() {
        int ai = randomInt(HAND.length);
        int bi = randomInt(HAND.length);
        System.out.println("---");
        System.out.println("Aさん: " + HAND[ai]);
        System.out.println("Bさん: " + HAND[bi]);
        System.out.println("結果 : " + MATRIX[ai][bi]);
    }

    // 指定範囲（0max）の整数の乱数を返す。
    static int randomInt(int max) {
        return randomInt(0, max);
    }

    // 指定範囲（nmax）の整数の乱数を返す。
    static int randomInt(int n, int max) {
        return (int) (Math.random() * max) + n;
    }
}
