package com.thoughtworks;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FormatCheckUtil {

    private static Pattern inputPattern;
    private static Pattern namePattern;
    private static Pattern phonePattern;
    private static Pattern emailPattern;
    private static Pattern passwordPattern;

    static {
        String inputFormat = "[^,]+,[^,]+,[^,]+,[^,]+";
        String nameFormat = ".{2,10}";
        String phoneFormat = "^1\\d{10}";
        String emailFormat = ".+@.+";
        String passwordFormat = ".*((\\d.*[a-zA-z])|([a-zA-z].*\\d)).*";

        inputPattern = Pattern.compile(inputFormat);
        namePattern = Pattern.compile(nameFormat);
        phonePattern = Pattern.compile(phoneFormat);
        emailPattern = Pattern.compile(emailFormat);
        passwordPattern = Pattern.compile(passwordFormat);
    }

    private static boolean formatCheck(Pattern pattern, String text) {
        return pattern.matcher(text).matches();
    }

    public static boolean inputFormatCheck(String input) {
        return formatCheck(inputPattern, input);
    }

    public static Map<String, Boolean> entitiesFormatCheck(String input) {
        Map<String, Boolean> result = new LinkedHashMap<>();
        String[] entitiesCollection = input.split(",");
        result.put("name", formatCheck(namePattern,entitiesCollection[0] ));
        result.put("phone", formatCheck(phonePattern,entitiesCollection[1] ));
        result.put("email", formatCheck(emailPattern,entitiesCollection[2] ));
        result.put("password", formatCheck(passwordPattern,entitiesCollection[3] ));
        return result;
    }

}
