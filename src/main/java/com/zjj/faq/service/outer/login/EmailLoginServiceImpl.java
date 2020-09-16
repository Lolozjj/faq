package com.zjj.faq.service.outer.login;

import com.zjj.faq.batis.redis.RedisString;
import com.zjj.faq.batis.shiro.JwtUtil;
import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.entity.User;
import com.zjj.faq.service.inter.EmailService;
import com.zjj.faq.service.inter.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ：zjj
 * @description： 邮箱登录服务
 * @date ：2020/9/8 0008 10:04
 */

@Service
public class EmailLoginServiceImpl extends BaseLogin implements LoginService {

    private final EmailService emailService;

    public EmailLoginServiceImpl(RedisString redisString, UserService userService, EmailService emailService) {
        super(redisString, userService);
        this.emailService = emailService;
    }

    /**
     * 邮箱登录验证
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

        if (realPassword == null) {
            return Msg.fail().add("info","用户名错误");
        }
        if (!realPassword.equals(inputPassword)) {
            return Msg.fail().add("info","密码错误");
        }
        if(captchaText==null||!redisString.getOne(captchaUid).equals(captchaText.toLowerCase().trim())){
            return Msg.fail().add("info","验证码错误");
        }
        String account=userService.getAccountByEmail(email);
        return Msg.success().add("token", JwtUtil.createToken(account));

    }
    /**
     * 注册账号时给邮箱发送的验证信息
     * @param email 邮箱
     * @return 发送邮箱的结果
     */
    @Override
    public Msg captchaByRegister(String email) {
        return emailService.sendEmail(email);
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
     * @return 注册结果,账号
     */
    @Override
    public Msg register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setState(1);
        user.setSalt("2000");
        user.setAccount(randomAccount());
        int resultSum = userService.add(user);
        if(resultSum==0) {
            return Msg.fail().add("info","注册失败");
        }else{
            return Msg.success().add("account",user.getAccount());
        }
    }


}
