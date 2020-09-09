package com.zjj.faq.batis.exception;


import com.zjj.faq.batis.utils.Msg;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chen
 * @date 2019/7/23
 * @email 15218979950@163.com
 * @description 对异常进行返回处理
 */
@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public Msg handle401() {
        return Msg.noPermission().add("info","您没有权限访问！");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public Msg globalException(HttpServletRequest request, Throwable ex) {
        return Msg.code(getStatus(request).value()).add("info","访问出错，无法访问: " + ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
