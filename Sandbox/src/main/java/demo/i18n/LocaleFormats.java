package demo.i18n;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class LocaleFormats {

    public static void main(String[] args) {
        long number = 5000000L;

        NumberFormat numberFormatDefault = NumberFormat.getInstance();
        System.out.println("Number Format using Default Locale: "
                           + numberFormatDefault.format(number));

        NumberFormat numberFormatLocale = NumberFormat.getInstance(Locale.FRENCH);
        System.out.println("Number Format using French Locale: "
                           + numberFormatLocale.format(number));

        NumberFormat numberFormatDefaultCurrency = NumberFormat.getCurrencyInstance();
        System.out.println("Currency Format using Default Locale: "
                           + numberFormatDefaultCurrency.format(number));

        NumberFormat numberFormatLocaleCurrency = NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println("Currency Format using UK Locale: "
                           + numberFormatLocaleCurrency.format(number));

        numberFormatLocaleCurrency = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        Currency currency = Currency.getInstance(Locale.JAPAN);
        System.out.println("Currency Format using Japanese Locale: "
                           + numberFormatLocaleCurrency.format(number)
                           + "\nCurrency Display Name : " + currency.getDisplayName()
                           + "\nCurrency Code : " + currency.getCurrencyCode());

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);
        System.out.println("Date Format in French Locale: "
                           + dateFormat.format(new Date()));
    }
}
