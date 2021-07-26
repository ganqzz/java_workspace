package java8tips.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class DatesAndTimes {

    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);

        LocalDate specificDate = LocalDate.of(2000, 1, 1);
        System.out.println(specificDate); //=> 2000-01-01

        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime);

        LocalTime specificTime = LocalTime.of(14, 0, 45);
        System.out.println(specificTime);

        LocalDateTime currentDT = LocalDateTime.now();
        System.out.println(currentDT);

        LocalDateTime specificDT = LocalDateTime.of(specificDate, specificTime);
        System.out.println(specificDT);

        // Period
        Period p = currentDate.until(specificDate);
        System.out.println(p.getYears());
        System.out.println(p.getMonths());
        System.out.println(p.getDays());
    }
}
