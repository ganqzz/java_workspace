package com.cardsForest.platform;

/**
 * Shorthands by static import
 */
public class Shorthands {

    public static void print(Object str) {
        System.out.println(str);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void error(String str) {
        print("Error: " + str);
        System.exit(1);
    }
}
