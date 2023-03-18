package com.chat.message.util;

import java.util.Random;

public class VerificationCodeGenerator {

    public static String generateVerificationCode(int length) {
        String characters = "0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
