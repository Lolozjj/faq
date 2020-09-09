package com.zjj.faq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/8 0008 9:46
 */
@RestController
public class BaseController {
    @GetMapping("/noPermissions")
    public String noPermissions(){
        return "无权限";
    }
}
