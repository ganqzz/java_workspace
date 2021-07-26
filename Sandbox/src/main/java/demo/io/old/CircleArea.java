package demo.io.old;

// CircleArea

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CircleArea {

    public static void main(String[] args) {
        double r = 0; // 半径
        BufferedReader br = null; // finallyでも使うため、ここで宣言。

        try { // BufferedReader用
            br = new BufferedReader(new InputStreamReader(System.in)); // 標準入力を開く。

            // 無限ループ
            while (true) {
                System.out.println("円の半径を正の数値で入力してください。");

                try { // NumberFormatException用
                    r = Double.parseDouble(br.readLine()); // 半径rを読み取る。
                } catch (NumberFormatException e) {
                    System.out.println("不正な文字列です。");
                    continue; // 頭に戻る。
                }

                if (r <= 0) {
                    System.out.println("負の数値です。");
                    continue; // 頭に戻る。
                }

                break; // 合格なので、ループから脱出。
            } // endWhile

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close(); // BufferedReaderクローズ
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 計算と表示
        System.out.println("円（半径 = " + r + "）の面積は、" + calcCircleArea(r));
    }

    // 面積計算
    private static double calcCircleArea(double r) {
        return Math.PI * r * r;
    }
}
