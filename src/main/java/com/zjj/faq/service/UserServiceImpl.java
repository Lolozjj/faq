package com.zjj.faq.service;

import com.zjj.faq.entity.Permission;
import com.zjj.faq.entity.Role;
import com.zjj.faq.entity.User;
import com.zjj.faq.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/7 0007 20:42
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{


    @Autowired
    private UserMapper userMapper;

    @Override
    public String getPassword(String email) {
        return userMapper.getPassword(email);
    }

    @Override
    public Integer getState(String name) {
        return null;
    }

    @Override
    public List<Role> getRoles(String name) {
        return null;
    }

    @Override
    public List<Permission> getPermission(String name) {
        return null;
    }

    @Override
    public String encryption(String password){
        Object result = new SimpleHash(
                "MD5",
                password,
                ByteSource.Util.bytes(2000+""),
                2
        );//通过SimpleHash得到MD5加密过的密文密码，此处要与UserRealm的认证，以及ShiroConfig的HashedCredentialsMatcher配合使用
        return result.toString();
    }

    @Override
    public int add(User user) {
        user.setPassword(encryption(user.getPassword()));
        return userMapper.insert(user);
    }




}
