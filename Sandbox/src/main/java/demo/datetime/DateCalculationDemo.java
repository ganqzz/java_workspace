package demo.datetime;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateCalculationDemo {
    public static void main(String[] args) {
        GregorianCalendar today = new GregorianCalendar();
        // GregorianCalendar today = new GregorianCalendar(2014, 11, 26); // テスト用
        // GregorianCalendar today = new GregorianCalendar(2019, 11, 26); // テスト用（うるう年）

        int thisYear = today.get(Calendar.YEAR); // 今年
        int nextYear = today.get(Calendar.YEAR) + 1; // 来年
        int nextXmas; // 次のクリスマスまでの日数。宣言のみ。
        GregorianCalendar xmas; // 宣言のみ。

        if (today.get(Calendar.MONTH) == Calendar.DECEMBER &&
            today.get(Calendar.DAY_OF_MONTH) > 25) {
            // 12/26〜12/31
            // 来年のクリスマス
            xmas = new GregorianCalendar(nextYear, Calendar.DECEMBER, 25);
            nextXmas = xmas.get(Calendar.DAY_OF_YEAR) + 31 - today.get(Calendar.DAY_OF_MONTH);
        } else {
            // 1/1〜12/25
            // 今年のクリスマス
            xmas = new GregorianCalendar(thisYear, Calendar.DECEMBER, 25);
            nextXmas = xmas.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
        }

        // System.out.println(xmas);

        // 表示
        System.out.println("クリスマスまで、" + nextXmas + "日");
    }
}
