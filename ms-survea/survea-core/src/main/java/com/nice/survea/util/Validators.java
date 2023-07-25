package com.nice.survea.util;

import java.util.regex.Pattern;

public abstract class Validators {

    private static Pattern pattern = Pattern.compile("^[A-Za-z0-9\\s]+$");

    public static boolean validateName(String name) {
        return pattern.matcher(name).matches();
    }

    public static boolean validateTextAnswer(String text) {
            return text == null || text.matches(".*\\w.*");
    }

    public static boolean validateRangeAnswer(String text, int min, int max) {
        int number;
        if (text != null) {
            try {
                number = Integer.parseInt(text);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return number >= min && number <= max;
        }
        return true;
    }
}
