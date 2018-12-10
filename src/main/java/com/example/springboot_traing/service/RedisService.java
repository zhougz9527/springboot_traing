package com.example.springboot_traing.service;

/**
 * @Author: Think
 * @Date: 2018/12/10
 * @Time: 14:52
 */
public interface RedisService {
    static String MAIL = "send_mail_interval_limit:";
    static String CODE = "mail_code:";
    static int MAIL_EXPIRES = 60000;
    static int CODE_EXPIRES = 300000;

    // 设置key
    boolean set(String key, Object value);

    // 设置key, 过期时间
    boolean set(String key, Object value, long time);

    // 设置value
    Object get(String key);

    // 判断key是否存在
    boolean exists(String key);

    // 删除key
    boolean delete(String key);

    // 设置过期时间
    boolean expire(String key, long timeout);

    // 获取key的过期时间
    long getExpireTime(String key);

}
