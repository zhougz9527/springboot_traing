package com.example.springboot_traing.service;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Think
 * @Date: 2018/12/10
 * @Time: 14:52
 */
@Service
public class RedisServiceImpl extends BaseServiceImpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;


    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);//单独设置valueSerializer
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean set(String key, Object value) {
        boolean success = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("存入redis失败: {}", e.getMessage());
        }
        return success;
    }

    @Override
    public boolean set(String key, Object value, long time) {
        boolean success = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("存入redis失败: {}", e.getMessage());
        }
        return success;
    }

    @Override
    public Object get(String key) {
        Object object = redisTemplate.opsForValue().get(key);
        return object;
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public boolean delete(String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }

    @Override
    public boolean expire(String key, long timeout) {
        if (exists(key)) {
            return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return false;
    }

    @Override
    public long getExpireTime(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }
}
