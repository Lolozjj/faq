package com.zjj.faq.batis.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Random;

/**
 * 邮件工具类
 * @author 阿呆的小鸡仔
 */
@Slf4j
public class MailUtil {

    /**
     * SMTP发送服务器的名字,普通qq号只能是smtp.qq.com
     */
    private static final String HOST_NAME="smtp.qq.com";
    /**
     * 鉴权端口
     */
    private static final int HOST_PORT = 465;
    /**
     * 邮件编码集
     */
    private static final String CHARSET  = "utf-8";
    /**
     * 发送者邮箱
     */
    private static final String SENDER_EMAIL = "1783763662@qq.com";
    /**
     * 发送者名称
     */
    private static final String SENDER_NAME = "zjj";
    /**
     * 发送者邮箱授权码
     */
    private static final String AUTHORIZATION_CODE= "prfzwhjaokwddffa";
    /**
     * 验证码生成范围
     */
    private static final String VERIFY_CODES = "0123456789";




    /**
     * 给邮箱发送验证码
     * @param emailAddress 邮箱地址
     * @return 生成的验证码
     */
    public static String sendEmail(String emailAddress) throws EmailException {
        HtmlEmail email=new HtmlEmail();
        email.setHostName(HOST_NAME);
        //设置需要鉴权端口
        email.setSmtpPort(HOST_PORT);
        //开启 SSL 加密
        email.setSSLOnConnect(true);
        // 字符编码集的设置
        email.setCharset(CHARSET);
        // 发送人的邮箱
        email.setFrom(SENDER_EMAIL, SENDER_NAME);
        // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和得到的授权码
        email.setAuthentication(SENDER_EMAIL, AUTHORIZATION_CODE);

        String code=generateVerifyCode(6);
        // 收件人的邮箱
        email.addTo(emailAddress);
        // 设置邮件主题
        email.setSubject("注册验证码");
        // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
        email.setMsg("欢迎注册，您的验证码为："+code+"\n@orhe.cn");
        // 发送
        email.send();
        System.out.println ( "邮件发送成功!" );
        log.info(emailAddress+"  地址的邮箱已经成功发送");
        return code;
    }

    /**
     * 生成验证码
     * @param verifySize 验证码长度
     * @return 验证码
     */
    private static String generateVerifyCode(int verifySize){
        String sources=VERIFY_CODES;
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }
}