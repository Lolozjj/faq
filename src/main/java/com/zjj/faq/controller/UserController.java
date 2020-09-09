package com.zjj.faq.controller;

import com.zjj.faq.entity.User;
import com.zjj.faq.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/7 0007 23:16
 */
@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @RequiresRoles(logical = Logical.OR, value = {"boss"})
    private String saveUser(@RequestBody User user){
       return userService.add(user);
    }
}
