package com.cck.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisUtils {
    public static RedisTemplate getRedisTemplate(){
        //获取redisTemplate对象
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //设置序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    //在redis存入数据
    public static void setValue(String key,Object value){
        getRedisTemplate().opsForValue().set(key,value);
    }

    //在redis中取出数据
    public static Object getValue(String key){
        return getRedisTemplate().opsForValue().get(key);
    }
}
