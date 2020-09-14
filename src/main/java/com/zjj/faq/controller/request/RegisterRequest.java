package com.zjj.faq.controller.request;

import lombok.Data;

/**
 * @author 阿呆的小鸡仔
 */
@Data
public class RegisterRequest {
    /**
     * 邮箱
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
