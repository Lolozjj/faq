package com.zjj.faq.batis.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public abstract class RedisOperation {

    @Autowired
    protected StringRedisTemplate stringRedisTemplate;
}
