package com.thoughtworks.interfaces;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FormatCheckUtil {

    private static Pattern registerPattern;
    private static Pattern loginPattern;
    private static Pattern namePattern;
    private static Pattern phonePattern;
    private static Pattern emailPattern;
    private static Pattern passwordPattern;
    public static Map<String, String> entityTranslator = new HashMap<String, String>() {
        {
            put("username", "用户名");
            put("phone", "手机号");
            put("email", "邮箱");
            put("password", "密码");
        }
    };

    static {
        String registerFormat = "[^,]+,[^,]+,[^,]+,[^,]+";
        String loginFormat = "[^,]+,[^,]";
        String nameFormat = ".{2,10}";
        String phoneFormat = "^1\\d{10}";
        String emailFormat = ".+@.+";
        String passwordFormat = ".*((\\d.*[a-zA-z])|([a-zA-z].*\\d)).*";

        registerPattern = Pattern.compile(registerFormat);
        loginPattern = Pattern.compile(loginFormat);
        namePattern = Pattern.compile(nameFormat);
        phonePattern = Pattern.compile(phoneFormat);
        emailPattern = Pattern.compile(emailFormat);
        passwordPattern = Pattern.compile(passwordFormat);
    }

    private static boolean formatCheck(Pattern pattern, String text) {
        return pattern.matcher(text).matches();
    }

    public static boolean registerFormatCheck(String input) {
        return formatCheck(registerPattern, input);
    }

    public static boolean loginFormatCheck(String input) {
        return formatCheck(loginPattern, input);
    }

    public static Map<String, Boolean> registerEntitiesFormatCheck(String input) {
        Map<String, Boolean> result = new LinkedHashMap<>();
        String[] entitiesCollection = input.split(",");
        result.put("name", formatCheck(namePattern, entitiesCollection[0]));
        result.put("phone", formatCheck(phonePattern, entitiesCollection[1]));
        result.put("email", formatCheck(emailPattern, entitiesCollection[2]));
        result.put("password", formatCheck(passwordPattern, entitiesCollection[3]));
        return result;
    }

}
