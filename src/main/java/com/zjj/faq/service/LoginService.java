package com.zjj.faq.service;

import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.entity.response.CaptchaResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 阿呆的小鸡仔
 */
public interface LoginService {
    /**
     * 登录获取图形验证码
     * @param request 请求
     * @param response 响应
     * @return 验证码的id和具体内容
     * @throws IOException io异常
     */
    public CaptchaResponse getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 用户登录
     * @param account 账号或邮箱或手机号等
     * @param password 密码或登录验证码
     * @param captchaUid 图形验证码id
     * @param captchaText 图形验证码内容
     * @return 登录结果
     */
    public Msg login(String account, String password, String captchaUid, String captchaText);


    /**
     * 注册账号所需要的验证码（手机验证，或者图形验证）
     * @param account 邮箱号或手机号等
     * @return 发送结果
     */
    public Msg captchaByRegister(String account);


    /**
     * 注册时需要验证下验证码是否正确
     * @param uuid 验证码的uuid
     * @param text 验证码的内容
     * @return 验证结果
     */
    public Msg validationByRegister(String uuid,String text);

    /**
     * 注册账号
     * @param account 账号或手机号或邮箱
     * @param password 密码
     * @return 注册结果
     */
    public Msg register(String account,String password);

}
