package com.masayukikaburagi.model.validation;

import com.masayukikaburagi.model.entity.Loclog;

import java.util.HashMap;
import java.util.Map;

/**
 * Loclog model validator
 * TODO: implementations
 */
public class LoclogValidator {

    public static Map<String, String> rules = new HashMap<>();

    static {
        // フィールド毎のルール定義
        rules.put("userName", "required|min:4|max:30");
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean isBelowMax(int i, int max) {
        return i <= max;
    }

    public static boolean isBetween(int i, int min, int max) {
        return i >= min && i <= max;
    }

    public static boolean isStringLengthBetween(String s, int min, int max) {
        return isBetween(s.length(), min, max);
    }

    public static Map<String, Object> validate(Loclog loclog) {
        Map<String, Object> errors = new HashMap<>();

        // TODO: implementations

        return errors;
    }

    public static Map<String, Object> validate(Loclog loclog, Map<String, String> rules) {
        Map<String, Object> errors = new HashMap<>();

        // TODO: implementations

        return errors;
    }
}
