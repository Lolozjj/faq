package com.zjj.faq.batis.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author 阿呆的小鸡仔
 */
public abstract class BaseRedisOperation {

    @Autowired
    protected StringRedisTemplate stringRedisTemplate;
}
