package com.zjj.faq.batis.exception;

/**
 * list 转 map 异常
 */
public class ListToMapException extends RuntimeException {
    public ListToMapException() {
    }

    public ListToMapException(String message) {
        super(message);
    }
}
