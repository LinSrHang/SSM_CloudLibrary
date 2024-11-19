package com.to404hanga.utils;

import java.util.regex.Pattern;

public class VerifyEmail {
    public static boolean isValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
    }
}
