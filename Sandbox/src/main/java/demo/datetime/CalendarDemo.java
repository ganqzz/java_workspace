package demo.datetime;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

class CalendarDemo {
    public static void main(String args[]) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar instanceof GregorianCalendar); // => true

        System.out.println(Calendar.SUNDAY); //=> 1
        System.out.println(Calendar.MONDAY);
        System.out.println(Calendar.TUESDAY);
        System.out.println(Calendar.WEDNESDAY);
        System.out.println(Calendar.THURSDAY);
        System.out.println(Calendar.FRIDAY);
        System.out.println(Calendar.SATURDAY);

        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.MINUTE));

        Calendar calendarEST = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
        Calendar calendarJST = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        System.out.println("EST hour: " + calendarEST.get(Calendar.HOUR_OF_DAY) + ":"
                           + calendarEST.get(Calendar.MINUTE));
        System.out.println("JST hour: " + calendarJST.get(Calendar.HOUR_OF_DAY) + ":"
                           + calendarJST.get(Calendar.MINUTE));

        Calendar today = Calendar.getInstance();

        System.out.println(today.get(Calendar.DAY_OF_WEEK));
        // 英語、米国
        System.out.println(today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale(
                "en", "US")));
        // フランス語、フランス
        System.out.println(today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale(
                "fr", "FR")));
        // ドイツ語、ドイツ
        System.out.println(today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale(
                "de", "DE")));
        // 日本語、日本
        System.out.println(today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale(
                "ja", "JP")));
        // 和暦
        Calendar wareki = Calendar.getInstance(new Locale("ja", "JP", "JP"));
        System.out.println(wareki.get(Calendar.ERA));
        System.out.println(wareki.get(Calendar.YEAR));

        // System.out.println(today.after(new Date())); // Source参照
    }
}
