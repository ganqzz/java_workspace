package demo.datetime;

import java.util.Date;

class DateDemo {
    public static void main(String args[]) {
        // Dateオブジェクトを現在の
        // 日付/時刻で初期化する
        Date currentDate = new Date();

        // 現在の日付を表示する
        System.out.println(currentDate); // toString()がいいように表示してくれる。

        // Dateオブジェクトを基準時
        // （1970/1/1）に初期化する
        Date epoch = new Date(0);

        // 基準時を表示する
        System.out.println(epoch);

        // 100日後
        Date date = new Date();
        long curr = date.getTime(); // ms : long
        date.setTime(curr + 100 * 24 * 60 * 60 * 1000L); // long型で計算させること。
        System.out.println(date);
    }
}
