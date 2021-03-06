package com.zjj.faq.service.inter;

import com.zjj.faq.batis.redis.RedisString;
import com.zjj.faq.batis.utils.MailUtil;
import com.zjj.faq.batis.utils.Msg;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

/**
 * @author 阿呆的小鸡仔
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final RedisString redisString;

    public EmailServiceImpl(RedisString redisString) {
        this.redisString = redisString;
    }


    /**
     * 给邮箱发送验证，并将邮箱做为key,验证码作为value存入redis
     * @param email 邮箱
     * @return 发送邮箱的结果
     */
    @Override
    public Msg sendEmail(String email) {
        try {
            String code = MailUtil.sendEmail(email);
            redisString.setOne(email,code,1000*60*5);
            return Msg.success();
        } catch (EmailException e) {
            log.error(email+"邮件发送信息错误");
            e.printStackTrace();
            return Msg.fail();
        }
    }
}
