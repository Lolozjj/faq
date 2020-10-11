package com.zjj.faq.controller.login.request;

import lombok.Data;

/**
 * @author 阿呆的小鸡仔
 */
@Data
public class LoginRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     *  验证码id
     */
    private String captchaUid;
    /**
     * 验证码内容
     */
    private String captchaText;
}
