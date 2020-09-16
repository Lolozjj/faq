package com.zjj.faq.controller;

import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.controller.request.LoginRequest;
import com.zjj.faq.controller.request.RegisterRequest;
import com.zjj.faq.controller.request.ValidationEmailRequest;
import com.zjj.faq.service.outer.login.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author ：zjj
 * @description： 登录控制器
 * @date ：2020/9/8 0008 9:53
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    @Autowired
    private LoginService loginService;


    @ApiOperation(value = "请求登录图形验证码", notes = "验证码--不进行拦截")
    @RequestMapping("/captcha")
    public Msg captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return loginService.getCaptcha(request,response);
    }

    @ApiOperation(value = "请求邮箱验证码", notes = "验证码--不进行拦截")
    @GetMapping("/emailCaptcha/{email}")
    public Msg captchaByEmail(@PathVariable("email") @Valid @Email String email){
        return loginService.captchaByRegister(email);
    }

    @ApiOperation(value = "请求邮箱验证码", notes = "验证码--不进行拦截")
    @PostMapping("/emailCaptcha")
    public Msg validationByEmail(ValidationEmailRequest validationEmailRequest){
        return loginService.validationByRegister(validationEmailRequest.getEmail(), validationEmailRequest.getCaptchaText());
    }

    @ApiOperation(value = "用户登录", notes = "登录--不进行拦截")
    @PostMapping("/login")
    public Msg login(@RequestBody @Valid @NotBlank LoginRequest loginRequest){
        return loginService.login(loginRequest.getUsername(),loginRequest.getPassword(),loginRequest.getCaptchaUid(),loginRequest.getCaptchaText());
    }

    @ApiOperation(value = "用户注册", notes = "注册--不进行拦截")
    @PostMapping("/register")
    public Msg register(@RequestBody @Valid @NotBlank RegisterRequest registerRequest){
        return loginService.register(registerRequest.getAccount(),registerRequest.getPassword());
    }


}
