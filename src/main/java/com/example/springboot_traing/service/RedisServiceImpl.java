package com.example.springboot_traing.service;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        return exists(key) ? redisTemplate.opsForValue().get(key) : null;
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public boolean delete(String key) {
        return exists(key) ? redisTemplate.delete(key) : true;
    }

    @Override
    public boolean expire(String key, long timeout) {
        return exists(key) ? redisTemplate.expire(key, timeout, TimeUnit.SECONDS) : false;
    }

    @Override
    public long getExpireTime(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }
}
