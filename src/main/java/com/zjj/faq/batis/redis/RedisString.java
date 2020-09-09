package com.zjj.faq.batis.redis;

import com.zjj.faq.batis.exception.ListToMapException;
import com.zjj.faq.batis.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisString extends RedisOperation {

    /**
     * 设置一个字符串类型的值
     * @param key 键
     * @param value 值
     * @param timeout 存在时间，单位毫秒
     */
    public void setOne(String key,String value,long timeout){
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     *
     * 设置一个字符串类型的值
     * @param key 键
     * @param value 值
     * @param timeout 存在时间，单位毫秒
     * @return 存在返回true,否则false
     */
    public boolean setOneIfAbsent(String key,String value,long timeout){
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 得到key对应的value
     * @param key 键
     * @return 获取值,key不存在返回null
     */
    public String getOne(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }


    /**
     * 设置多个字符串类型的值
     * @param keys 键列表
     * @param values 值列表
     * @throws ListToMapException 列表转换异常
     */
    public void setMulti(List<String> keys,List<String> values) throws ListToMapException {

        stringRedisTemplate.opsForValue().multiSet(CollectionUtils.toMap(keys,values));
    }

    /**
     * 设置多个字符串类型的值
     * @param keys 键列表
     * @param values 值列表
     * @return 批量插入，如果里面的所有key都不存在，则全部插入，返回true，如果其中一个在redis中已存在，全不插入，返回false
     */
    public boolean setMultiIfAbsent(List<String> keys,List<String> values){
        return stringRedisTemplate.opsForValue().multiSetIfAbsent(CollectionUtils.toMap(keys,values));
    }


    /**
     * 获取列表对应键值
     * @param keys 键列表
     * @return 批量获取，key不存在返回null
     */
    public List<String> getMulti(List<String> keys){
        if(keys==null)
            return null;
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }


    /**
     * 返回键所对应的值的字符串长度
     * @param key 键
     * @return 返回值长度，键不存在则返回0
     */
    public Long getLength(String key) {
        return stringRedisTemplate.opsForValue().size(key);
    }


    /**
     * 往key追加值
     * @param key 键
     * @param value 追加值
     * @return 追加后值总长度
     */
    public Integer append(String key,String value) {
        return stringRedisTemplate.opsForValue().append(key, value);
    }


    /**
     * 将该key的值减1，如果不是整形则报异常，key不存在就创建一个值为0的key
     * @param key 键
     */
    public void decrement(String key) {
        stringRedisTemplate.opsForValue().decrement(key);
    }

    /**
     * 将该key的值减1，如果不是整形则报异常，key不存在就创建一个值为0的key
     * @param key 键
     */
    public void increment(String key) {
        stringRedisTemplate.opsForValue().increment(key);
    }


    /**
     * 删除该键
     * @param key 键
     * @return 删除结果
     */
    public boolean delete(String key) {
        return stringRedisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 删除列表中的键
     * @param keys 键
     * @return 删除键的个数
     */
    public Long delete(List<String> keys) {
        return stringRedisTemplate.opsForValue().getOperations().delete(keys);
    }
}
