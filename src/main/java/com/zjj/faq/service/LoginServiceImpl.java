package com.zjj.faq.service;

import com.wf.captcha.SpecCaptcha;
import com.zjj.faq.batis.redis.RedisString;
import com.zjj.faq.batis.shiro.JWTUtil;
import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.entity.response.CaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/8 0008 10:04
 */

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisString redisString;

    @Autowired
    private UserService userService;

    /**
     * 设置登陆图片验证码
     * @param request 请求
     * @param response 响应
     * @return 该验证码的uui
     * @throws IOException io异常
     */
    @Override
    public CaptchaResponse getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String uid = UUID.randomUUID().toString();
        redisString.setOne(uid,verCode,1000*60*5);
        return new CaptchaResponse(uid,specCaptcha.toBase64());
    }


    @Override
    public Msg login(String username, String password, String captchaUid, String captchaText) {
        String inputPassword = userService.encryption(password);
        String realPassword =userService.getPassword(username);
        System.out.println(realPassword);
        if (realPassword == null) {
            return Msg.fail().add("info","用户名错误");
        }
        if (!realPassword.equals(inputPassword)) {
            return Msg.fail().add("info","密码错误");
        }
        if(captchaText==null||!redisString.getOne(captchaUid).equals(captchaText.toLowerCase().trim())){
            return Msg.fail().add("info","验证码错误");
        }
        return Msg.success().add("token", JWTUtil.createToken(username));

    }
}
