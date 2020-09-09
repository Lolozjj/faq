package com.zjj.faq.controller;

import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.entity.response.CaptchaResponse;
import com.zjj.faq.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/8 0008 9:53
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 生成验证码
     * @param request 请求
     * @param response 响应
     * @throws Exception 验证码异常
     */
    @RequestMapping("/captcha")
    public CaptchaResponse captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return loginService.getCaptcha(request,response);
    }

    @ApiOperation(value = "用户登录", notes = "登录--不进行拦截")
    @PostMapping("/login")
    public Msg login(String username, String password, String captchaUid, String captchaText){
        return loginService.login(username,password,captchaUid,captchaText);
    }
}
