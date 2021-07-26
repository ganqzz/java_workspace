package etc;

import java.util.Random;

class RandomDemo {
    public static void main(String[] args) {
        // 乱数ジェネレータを作成する
        Random generator = new Random();

        // 10個のint型の乱数を生成する
        for (int i = 0; i < 10; i++) {
            System.out.println(generator.nextInt());
        }

        System.out.println();
        int num = 10; // 生成個数

        // numの設定
        if (args.length > 0) {
            try {
                num = Integer.parseInt(args[0]);
                if (num <= 0) num = 10; // 0以下の整数の場合は、デフォルトの10に。
            } catch (NumberFormatException e) {
            }
        }

        // 計算と表示
        System.out.println(num + " 個の乱数の平均 = " + avg(num));
    }

    // 引数: 個数n
    // 戻り値: n個の乱数の平均
    private static double avg(int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += new Random().nextGaussian();
        }

        return sum / n;
    }
}
