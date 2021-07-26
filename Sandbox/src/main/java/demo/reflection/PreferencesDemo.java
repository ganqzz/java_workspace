package demo.reflection;

import java.util.prefs.Preferences;

public class PreferencesDemo {

    public static void main(String[] args) {
        // 1
        Preferences userPreferences = Preferences.userRoot();
        Preferences systemPreferences = Preferences.systemRoot();

        // Store an int to User Preferences with String key
        userPreferences.putInt("X_DEFAULT", 10);

        // Retrieve an entry from User Preferences,
        // the number sent as a second parameter will be returned if the key doesnt exist
        int numberOfRows = userPreferences.getInt("X_DEFAULT", 5);
        System.out.println(numberOfRows);

        userPreferences.remove("X_DEFAULT");

        // 2
        Preferences userPreferencesPackage = Preferences.userNodeForPackage(PreferencesDemo.class);

        // Set
        userPreferencesPackage.put("USER_LANGUAGE", "SPANISH");

        // Retrieve
        System.out.println(userPreferencesPackage.get("USER_LANGUAGE", "ENGLISH"));

        // 3
        Preferences userPreferencesCustom = Preferences.userRoot().node("/user/custom/root");
        userPreferencesCustom.put("HOGE", "FUGA");
        System.out.println(userPreferencesCustom.get("HOGE", "FEFE"));
    }
}
