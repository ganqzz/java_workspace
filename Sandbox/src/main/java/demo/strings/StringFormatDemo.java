package demo.strings;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class StringFormatDemo {

    public static void main(String[] args) {
        // String.format "%[argument number] [flags] [width] [.precision] type"

        System.out.printf("%,10.2f %n", 1207354287.123);
        System.out.printf("%8.10f %n", Math.E);

        System.out.println("---");

        // Flags
        System.out.printf("%d\n", 32);
        System.out.printf("%o\n", 32);
        System.out.printf("%x\n", 32);
        System.out.printf("%#o\n", 32);
        System.out.printf("%#x\n", 32);
        System.out.printf("%#X\n", 32);

        System.out.println("---");

        System.out.printf("W:%d X:%d\n", 5, 235);
        System.out.printf("Y:%d Z:%d\n", 481, 12);
        System.out.printf("W:%4d X:%4d\n", 5, 235);
        System.out.printf("Y:%4d Z:%4d\n", 481, 12);
        System.out.printf("W:%04d X:%04d\n", 5, 235);
        System.out.printf("Y:%04d Z:%04d\n", 481, 12);
        System.out.printf("W:%-4d X:%-4d\n", 5, 235);
        System.out.printf("Y:%-4d Z:%-4d\n", 481, 12);

        System.out.println("---");

        System.out.printf("%d\n", 123);
        System.out.printf("%d\n", -456);
        System.out.printf("% d\n", 123);
        System.out.printf("% d\n", -456);
        System.out.printf("%+d\n", 123);
        System.out.printf("%+d\n", -456);
        System.out.printf("%(d\n", 123);
        System.out.printf("%(d\n", -456);
        System.out.printf("% (d\n", 123);

        System.out.println("---");

        // Argument index
        System.out.printf("%d %d %d\n", 100, 200, 300);
        System.out.printf("%3$d %1$d %2$d\n", 100, 200, 300);
        System.out.printf("%2$d %<04d %1$d\n", 100, 200);
        System.out.printf("%1$d %1$o 0x%1$04x\n", 123);

        System.out.println("---");

        System.out.printf("%tD %n", new Date());
        System.out.printf("%tD %n", LocalDate.now());

        Calendar cal = GregorianCalendar.getInstance();
        cal.set(1969, 6, 20);
        System.out.printf("%1$tm %1$td %1$tY\n", cal);
        System.out.printf("%1$tm %1$te %1$tY\n", cal);
        System.out.printf("%1$tB %1$tA %1$tY\n", cal);
        System.out.printf(Locale.US, "%1$tB %1$tA %1$tY\n", cal);
    }
}
