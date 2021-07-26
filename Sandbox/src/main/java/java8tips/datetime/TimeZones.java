package java8tips.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TimeZones {

    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dtf.format(dt));

        ZonedDateTime gmt = ZonedDateTime.now(ZoneId.of("GMT+0"));
        System.out.println(dtf.format(gmt));

        ZonedDateTime ny = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(dtf.format(ny));

        ZonedDateTime then = ZonedDateTime.of(
                LocalDate.of(2016, 2, 29),
                LocalTime.of(5, 30),
                ZoneId.of("Asia/Tokyo"));
        System.out.println(dtf.format(then));

        System.out.println();

        ZoneId.getAvailableZoneIds().stream()
              .filter(str -> str.contains("Europe"))
              .forEach(System.out::println);
    }

}
