package com.zjj.faq.batis.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @date 2019/7/23
 * @email 15218979950@163.com
 * @description  用于返回前端json数据的工具类
 */
public class Msg {

    // 状态码
    @Getter
    @Setter
    private int status;
    // 提示信息
    @Getter
    @Setter
    private String message;

    // 封装有效数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public static Msg success() {
        Msg result = new Msg();
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setStatus(400);
        result.setMessage("fail");
        return result;
    }

    public static Msg noPermission() {
        Msg result = new Msg();
        result.setStatus(401);
        result.setMessage("no permission");
        return result;
    }

    public static Msg error() {
        Msg result = new Msg();
        result.setStatus(500);
        result.setMessage("error");
        return result;
    }

    public static Msg code(int code){
        Msg result = new Msg();
        result.setStatus(code);
        result.setMessage("exception");
        return result;
    }

    public Msg add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
