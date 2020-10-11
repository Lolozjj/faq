package com.zjj.faq.service.outer.login.response;

import lombok.Data;

/**
 * @author 阿呆的小鸡仔
 */
@Data
public class AdminUserResponse {
    private String account;
    private String username;
    private String email;
    private String phone;
    private Integer state;
}
