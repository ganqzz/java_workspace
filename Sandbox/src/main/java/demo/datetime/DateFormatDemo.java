package demo.datetime;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatDemo {
    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);

        GregorianCalendar gc = new GregorianCalendar(2004, 2, 1); // 2004/03/01
        System.out.println(gc);
        gc.add(GregorianCalendar.DATE, 1);
        Date d2 = gc.getTime();

        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
        String sd = df.format(d2);
        System.out.println(sd);

        System.out.println(df.format(d));
    }
}
