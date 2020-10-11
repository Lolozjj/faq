package com.zjj.faq.controller.basis;

import com.zjj.faq.batis.utils.Msg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zjj
 * @description： 基础控制器
 * @date ：2020/9/8 0008 9:46
 */
@RestController
public class BaseController {
    @GetMapping("/noPermissions/{message}")
    public Msg noPermissions(@PathVariable String message){
        System.out.println(message);
        return Msg.noPermission().add("info","没有权限");
    }
}
