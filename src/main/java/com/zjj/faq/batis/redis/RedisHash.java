package com.zjj.faq.batis.redis;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Gjing
 **/
@Component
public class RedisHash  extends BaseRedisOperation{


    public void putAll(String key, Map<String,String> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 如果变量值存在，在变量中可以添加不存在的的键值对，如果变量不存在，则新增一个变量，同时将键值对添加到该变量。添加成功返回true否则返回false
     * 
     * putIfAbsent(H key, HK hashKey, HV value)
     */
    public Boolean putIfAbsent(String key,String hashKey,String value) {
        return stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 获取指定变量中的hashMap值。
     * 
     * values(H Key)
     * @return
     */
    public List<Object> values(String key) {
        return stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * 获取变量中的键值对。
     * 
     * entries(H key)
     */
    public Map<Object, Object> entries(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取变量中的指定map键是否有值,如果存在该map键则获取值，没有则返回null。
     * 
     * get(H key, Object hashKey)
     */
    public Object get(String key,String hashKey) {
        return stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取变量中的键。
     * 
     * keys(H key)
     */
    public Set<Object> keys(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     *  获取变量的长度
     *  
     *  size(H key)
     */
    public Long size(String  key) {
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * 使变量中的键以long值的大小进行自增长。值必须为Integer类型,否则异常
     * 
     * increment(H key, HK hashKey, long data)
     */
    public void increment() {
        Long increment = stringRedisTemplate.opsForHash().increment("hash", "hash-key2", 1);
        System.out.println(increment);
    }

    /**
     * 以集合的方式获取变量中的值。
     * 
     * multiGet(H key, Collection<HK> hashKeys)
     */
    public List<Object> multiGet(String key, Collection<Object> hashKeys) {
        return stringRedisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 匹配获取键值对，ScanOptions.NONE为获取全部键对，ScanOptions.scanOptions().match("hash-key2").build()匹配获取键位map1的键值对,不能模糊匹配。
     * 
     * scan(H key, ScanOptions options)
     */
    public void scan() {
        Cursor<Map.Entry<Object, Object>> scan = stringRedisTemplate.opsForHash().scan("hash", ScanOptions.NONE);
        while (scan.hasNext()) {
            Map.Entry<Object, Object> next = scan.next();
            System.out.println(next.getKey() + "---->" + next.getValue());
        }
    }

    /**
     * 删除变量中的键值对，可以传入多个参数，删除多个键值对。返回删除成功数量
     * 
     * delete(H key, Object... hashKeys)
     */
    public Long delete(String key,Collection hashKeys) {
        return stringRedisTemplate.opsForHash().delete(key, hashKeys);
    }

}
