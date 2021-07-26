package demo.i18n;

import java.util.Locale;

public class LocaleDemo {

    public static void main(String[] args) {
        // Locale instance
        System.out.println(new Locale("fr"));
        System.out.println(new Locale("fr", "CANADA"));
        System.out.println(new Locale("no", "NORWAY", "NY"));
        System.out.println(new Locale.Builder().setLanguage("en").setRegion("GB").build());
        System.out.println(Locale.forLanguageTag("en-GB"));
        System.out.println(Locale.FRANCE);

        System.out.println("--- Availble Locales");
        for (Locale aLocale : Locale.getAvailableLocales()) {
            System.out.println("Name of Locale: " + aLocale.getDisplayName());
            System.out.println("Language Code: " + aLocale.getLanguage()
                               + ", Language Display Name: "
                               + aLocale.getDisplayLanguage());
            System.out.println("Country Code: " + aLocale.getCountry()
                               + ", Country Display Name: "
                               + aLocale.getDisplayCountry());
            if (!aLocale.getScript().isEmpty()) {
                System.out.println("Script Code: " + aLocale.getScript()
                                   + ", Script Display Name: "
                                   + aLocale.getDisplayScript());
            }
            if (!aLocale.getVariant().isEmpty()) {
                System.out.println("Variant Code: " + aLocale.getVariant()
                                   + ", Variant Display Name: "
                                   + aLocale.getDisplayVariant());
            }
        }
    }
}
