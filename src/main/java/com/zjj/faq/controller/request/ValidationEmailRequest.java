package com.zjj.faq.controller.request;

import lombok.Data;

/**
 * @author 阿呆的小鸡仔
 */
@Data
public class ValidationEmailRequest {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱验证码
     */
    private String captchaText;
}
