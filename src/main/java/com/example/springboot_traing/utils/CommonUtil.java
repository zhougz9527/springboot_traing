package com.example.springboot_traing.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

/**
 * @Author: Think
 * @Date: 2018/12/10
 * @Time: 21:36
 */
public class CommonUtil {
    public static final String STRING = "String";
    public static final String INTEGER = "integer";
    private static final Logger logger = LogManager.getLogger();


    public static String random(int length, String type) {
        String string = "";
        switch (type) {
            case STRING:
                string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;
            case INTEGER:
                string = "0123456789";
                break;
        }
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(string.length());
            stringBuffer.append(string.charAt(number));
        }
        return stringBuffer.toString();
    }

    public static String md5(String s, boolean needSalt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (needSalt) {
                s = s + "springboot_traing@!#$%^*&_123456987654";
                byte[] bytes = md.digest(s.getBytes("utf-8"));
                return toHex(bytes);
            } else {
                md.update(s.getBytes());
                return new BigInteger(1, md.digest()).toString(16);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString().toLowerCase();
    }


}
