package com.zjj.faq.batis.utils;

import com.zjj.faq.batis.exception.ListToMapException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 容器转换工具
 * @author 阿呆的小鸡仔
 */
public class CollectionUtils {
    public static <T,K>  Map<T,K> toMap(List<T> keys, List<K> values){
        if (keys.size()!=values.size()){
            throw  new ListToMapException("keys size no equal values size");
        }
        Map<T,K> map=new HashMap<>(50);
        Iterator keyIt=keys.iterator();
        Iterator valueIt=values.iterator();
        while (keyIt.hasNext()&&valueIt.hasNext()){
            map.put((T) keyIt.next(),(K)valueIt.next());
        }
        return map;
    }



}
