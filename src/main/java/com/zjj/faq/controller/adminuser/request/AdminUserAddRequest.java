package com.zjj.faq.controller.adminuser.request;

import lombok.Data;

/**
 * @author 阿呆的小鸡仔
 */
@Data
public class AdminUserAddRequest {
    private String username;
    private String email;
    private String phone;
    private String password;
}
