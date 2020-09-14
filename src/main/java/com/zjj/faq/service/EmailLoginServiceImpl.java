package com.zjj.faq.service;

import com.wf.captcha.SpecCaptcha;
import com.zjj.faq.batis.redis.RedisString;
import com.zjj.faq.batis.shiro.JwtUtil;
import com.zjj.faq.batis.utils.MailUtil;
import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.entity.User;
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
public class EmailLoginServiceImpl implements LoginService {
    @Autowired
    private RedisString redisString;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

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

    /**
     * 登录验证
     * @param email 邮箱
     * @param password 密码
     * @param captchaUid 图形验证码id
     * @param captchaText 图形验证码内容
     * @return token或者失败信息
     */
    @Override
    public Msg login(String email, String password, String captchaUid, String captchaText) {
        String inputPassword = userService.encryption(password);
        String realPassword =userService.getPassword(email);
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
        return Msg.success().add("token", JwtUtil.createToken(email));

    }
    /**
     * 注册账号时给邮箱发送的验证信息
     * @param email 邮箱
     * @return 发送邮箱的结果
     */
    @Override
    public Msg captchaByRegister(String email) {
        Msg msg = emailService.sendEmail(email);
        return msg;
    }

    /**
     * 验证注册码是否正确
     * @param email 邮箱
     * @param text 验证码的内容
     * @return 验证结果
     */
    @Override
    public Msg validationByRegister(String email, String text) {
        String realText = redisString.getOne(email);
        if(realText==null||!realText.equals(text)){
            return Msg.fail().add("info","验证码错误");
        }
        return Msg.success();
    }

    /**
     * 注册账号
     * @param email 邮箱
     * @param password 密码
     * @return 注册结果
     */
    @Override
    public Msg register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setState(1);
        user.setSalt("2000");
        int resultSum = userService.add(user);
        if(resultSum==0) {
            return Msg.fail().add("info","注册失败");
        }else{
            return Msg.success();
        }
    }
}
