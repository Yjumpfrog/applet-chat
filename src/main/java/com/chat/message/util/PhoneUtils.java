package com.chat.message.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtils {
    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^1[3456789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
