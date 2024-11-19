package com.to404hanga.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Utils {
    private static final String salt = "biz_04h_md5_salt_";

    public static String encrypt(String pwd) {
        String encodeStr = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update((pwd + salt).getBytes(StandardCharsets.UTF_8));
            encodeStr = bytes2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String bytes2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String tmp;
        for (byte b : bytes) {
            tmp = Integer.toHexString(b & 0xFF);
            if (tmp.length() == 1) {
                // 对得到一位的字节进行补 0 操作
                stringBuilder.append("0");
            }
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }
}
