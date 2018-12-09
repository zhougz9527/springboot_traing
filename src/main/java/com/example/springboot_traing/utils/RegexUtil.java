package com.example.springboot_traing.utils;

import com.alibaba.fastjson.JSON;

import java.util.regex.Pattern;

/**
 *
 * 正则工具类
 *
 * @Author: Think
 * @Date: 2018/5/27
 * @Time: 16:06
 */
public class RegexUtil {

    /**
     * 正则表达式：验证昵称
     */
    public static final String REGEX_NICKNAME = "^[a-zA-Z0-9]\\w{4,17}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,12}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1(3|4|5|7|8)\\d{9}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：验证正整数
     */
    public static final String REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$";

    /**
     *
     * 校验用户名
     *
     * @param nickname
     * @return
     */
    public static boolean isNickname(String nickname) {
        return Pattern.matches(REGEX_NICKNAME, nickname);
    }

    /**
     *
     * 校验密码
     *
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     *
     * 校验手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     *
     * 校验邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     *
     * 校验中文
     *
     * @param chinese
     * @return
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     *
     * 校验身份证
     *
     * @param idCard
     * @return
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     *
     * 校验url
     *
     * @param url
     * @return
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     *
     * 校验ip
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     *
     * 校验正整数
     *
     * @param figure
     * @return
     */
    public static boolean isPositiveInteger(int figure) {
        return Pattern.matches(REGEX_POSITIVE_INTEGER, String.valueOf(figure));
    }

    /**
     *
     * 判断是否是json字符串
     *
     * @param json
     * @return
     */
    public static boolean isJson(String json) {
        boolean boo = false;
        try {
            Object object = JSON.parse(json);
            boo = true;
        } catch (Exception e) {
            boo = false;
        }
        return boo;
    }

}
