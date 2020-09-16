package com.zjj.faq.controller;

import com.zjj.faq.entity.User;
import com.zjj.faq.service.inter.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zjj
 * @description： user控制器
 * @date ：2020/9/7 0007 23:16
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @RequiresRoles(logical = Logical.OR, value = {"boss"})
    public int saveUser(@RequestBody User user){
       return userService.add(user);
    }
}
