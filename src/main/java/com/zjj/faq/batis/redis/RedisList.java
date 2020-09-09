package com.zjj.faq.batis.redis;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author zjj
 **/
@Component
public class RedisList extends RedisOperation{

    /**
     *  在变量左边添加元素值。
     * @param key 键
     * @param value 值
     * @return 如果key不存在会新建，添加成功返回添加后的总个数
     */
    public Long leftPush(String key,String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 向左边批量添加参数元素，如果key不存在会新建
     * @param key 键
     * @param values 值列表
     * @return 添加成功返回添加后的总个数
     */
    public Long leftPushAll(String key, Collection<String> values) {
        return stringRedisTemplate.opsForList().leftPushAll(key, values);

    }


    /**
     * 向集合最右边添加元素。如果key不存在会新建
     * @param key 键
     * @param value 值
     * @return 添加成功返回添加后的总个数
     */
    public Long rightPush(String key,String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 如果存在集合则在左侧添加元素。
     * @param key 键
     * @param value 值
     * @return 返回添加后总个数
     */
    public Long leftPushIfPresent(String key,String value) {
        return stringRedisTemplate.opsForList().leftPushIfPresent(key, value);
    }


    /**
     * 向右边批量添加元素
     * @param key 键
     * @param values 值列表
     * @return 返回当前集合元素总个数
     */
    public Long rightPushAll(String key,Collection<String> values) {
        return stringRedisTemplate.opsForList().rightPushAll(key, values);
    }


    /**
     * 向已存在的集合中右侧添加元素
     * @param key 键
     * @param value 值
     * @return 返回集合总元素个数
     */
    public Long rightPushIfPresent(String key,String value) {
        return stringRedisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 返回集合的元素个数
     * @param key 键
     * @return 总个数
     */
    public Long size(String key) {
        return stringRedisTemplate.opsForList().size("list2");
    }

    /**
     * 移除集合中的左边第一个元素,如果元素为空，该集合会自动删除
     * @param key 键
     * @return 返回删除的元素
     */
    public String leftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);

    }


    /**
     * 移除集合中的右边第一个元素,如果元素为空，该集合会自动删除
     * @param key 键
     * @return 返回删除的元素
     */
    public String rightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }


    /**
     * 移除第一个集合右边的一个元素，插入第二个集合左边插入这个元素
     * @param popKey 弹出的集合
     * @param pushKey 插入的集合
     * @return 操纵的值
     */
    public String rightPopAndLeftPush(String popKey,String pushKey) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(popKey, pushKey);

    }

    /**
     * 往指定位置插入值
     * @param key 集合名
     * @param index 下标
     * @param value 值
     */
    public void set(String key,long index,String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。count> 0：删除等于从左到右移动的值的第一个元素；
     * count< 0：删除等于从右到左移动的值的第一个元素；count = 0：删除等于value的所有元素
     * 
     * remove(K key, long count, Object value)
     */
    public Long remove(String key ,long count,String value) {
        return stringRedisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 截取集合元素长度，保留长度内的数据。
     * 
     * trim(K key, long start, long end)
     */
    public void trim(String key,long start, long end ) {
        stringRedisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 获取集合指定位置的值。
     * 
     * index(K key, long index)
     */
    public String index(String key,long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取指定区间的值。
     * 
     * range(K key, long start, long end)
     */
    public List<String> range(String key, long start, long end) {
        List<String> list = stringRedisTemplate.opsForList().range(key, start, end);
        return list;
    }

    /**
     * 删除指定集合,返回true删除成功
     * 
     * delete(K key)
     */
    public Boolean delete(String key) {
       return stringRedisTemplate.opsForList().getOperations().delete(key);
    }
}
