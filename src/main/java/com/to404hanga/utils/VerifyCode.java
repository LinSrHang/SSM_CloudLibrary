package com.to404hanga.utils;

import java.util.Random;

public class VerifyCode {
    // 剔除了 l, 1, I, 0, O 的字符集
    private static final String charSet = "23456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final Random random = new Random();

    public static String genVerifyCode(int length) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int r = random.nextInt(58);
            ret.append(charSet.charAt(r));
        }
        return ret.toString();
    }
}
