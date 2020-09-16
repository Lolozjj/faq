package com.zjj.faq.service.outer.login;

import com.wf.captcha.SpecCaptcha;
import com.zjj.faq.batis.redis.RedisString;
import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.entity.response.CaptchaResponse;
import com.zjj.faq.service.inter.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * 登录服务抽象类
 */
abstract class BaseLogin {

    /**
     * redis 缓存使用
     */
    final RedisString redisString;
    /**
     * 用户服务
     */
    final UserService userService;

    protected BaseLogin(RedisString redisString, UserService userService) {
        this.redisString = redisString;
        this.userService = userService;
    }

    /**
     * 随机生成账号
     * @return 生成的账号
     */
    String randomAccount(){
        int verifySize=10;
        String sources="1234567890abcdef";
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }


    /**
     * 设置登陆图片验证码
     * @param request 请求
     * @param response 响应
     * @return 该验证码的uui
     * @throws IOException io异常
     */

    public Msg getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String uid = UUID.randomUUID().toString();
        redisString.setOne(uid,verCode,1000*60*5);
        Msg msg = Msg.success();
        return msg.add("data",new CaptchaResponse(uid,specCaptcha.toBase64()));
    }
}
