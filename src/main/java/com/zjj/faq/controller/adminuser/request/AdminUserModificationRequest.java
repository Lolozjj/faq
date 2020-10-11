package com.zjj.faq.controller.adminuser.request;

import lombok.Data;

/**
 * @author 阿呆的小鸡仔
 */
@Data
public class AdminUserModificationRequest {
    private String account;
    private String username;
    private String email;
    private String phone;

}
