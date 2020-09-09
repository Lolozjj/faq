package com.zjj.faq.batis.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author chen
 * @date 2019/7/23
 * @email 15218979950@163.com
 * @description  对token进行扩展
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
