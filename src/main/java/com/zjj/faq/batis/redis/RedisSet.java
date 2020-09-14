package com.zjj.faq.batis.redis;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author Gjing
 *
 * 以下可能有部分方法含有参数传Collection的,本案例没有描述,你们可以根据实际参数类型传参
 **/
@Component
public class RedisSet extends BaseRedisOperation{


    /**
     * 向变量中批量添加值。返回添加的数量
     *
     * add(K key, V... values)
     */
    public Long add(String key, String... values) {
        return stringRedisTemplate.opsForSet().add(key,values);
    }

    /**
     * 获取变量的值
     *
     * members(K key)
     */
    public Set<String> members(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 获取变量中值得长度
     *
     * size(k key)
     */
    public Long size(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * 随机获取变量中的某个元素
     *
     * randomMember(k key)
     */
    public String randomMember(String key) {
        return stringRedisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取变量中指定个数的元素
     *
     * randomMembers(k key, long count)
     */
    public List<String> randomMembers(String key,long count) {
        return stringRedisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 检查给定的元素是否在变量中,true为存在
     *
     * isMember(k key, object value)
     */
    public Boolean isMember(String key,String value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 转义变量的元素值到另一个变量中
     *
     * move(k key, v value, k targetKey)
     */
    public Boolean move(String key,String value,String targetKey) {
        return stringRedisTemplate.opsForSet().move(key, value, targetKey);
    }

    /**
     * 弹出变量中的元素。当元素全部弹完,变量也会删除
     *
     * pop(k key)
     */
    public String pop(String key) {
        return stringRedisTemplate.opsForSet().pop(key);
    }

    /**
     * 批量删除变量中的元素,返回删除的数量
     *
     * remove(k key, v ...values)
     */
    public Long remove(String key,Object... values) {
        return stringRedisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 匹配获取键值对，ScanOptions.NONE为获取全部键值对；ScanOptions.scanOptions().match("C").build()匹配获取键位map1的键值对,不能模糊匹配。
     *
     * scan(K key, ScanOptions options)
     */
    public void scan() {
        Cursor<String> set = stringRedisTemplate.opsForSet().scan("set", ScanOptions.NONE);
        while (set.hasNext()) {
            String next = set.next();
            System.out.println(next);
        }
    }

    /**
     * 通过集合求差值。
     *
     * difference(k key, k otherKey)
     */
    public Set<String>  difference(String key,String otherKey) {
        return stringRedisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * 将求出来的差值元素保存
     *
     * differenceAndStore(K key, K otherKey, K targetKey)
     */
    public Long differenceAndStore(String key,String otherKey,String saveKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKey,saveKey);
    }

    /**
     * 获取去重的随机元素
     *
     * distinctRandomMembers(K key, long count)
     */
    public Set<String> distinctRandomMembers(String key,long count) {
        return stringRedisTemplate.opsForSet().distinctRandomMembers(key,count);
    }

    /**
     * 获取两个变量中的交集
     *
     * intersect(K key, K otherKey)
     */
    public Set<String>  intersect(String key,String otherKey) {
        return stringRedisTemplate.opsForSet().intersect(key,otherKey);
    }

    /**
     * 获取2个变量交集后保存到最后一个变量上。
     *
     * intersectAndStore(K key, K otherKey, K targetKey)
     */
    public Long intersectAndStore(String key,String otherKey,String saveKey) {
        return stringRedisTemplate.opsForSet().intersectAndStore(key,otherKey,saveKey);
    }

    /**
     * 获取两个变量的合集
     *
     * union(K key, K otherKey)
     */
    public Set<String> union(String key,String otherKey) {
        return stringRedisTemplate.opsForSet().union(key, otherKey);
    }

    /**
     * 获取两个变量合集后保存到另一个变量中
     *
     * unionAndStore(K key, K otherKey, K targetKey)
     */
    public Long unionAndStore(String key,String otherKey,String saveKey) {
        return stringRedisTemplate.opsForSet().unionAndStore(key,otherKey,saveKey);
    }
}
