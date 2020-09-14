package com.zjj.faq.service;

import com.zjj.faq.batis.utils.Msg;

/**
 * @author 阿呆的小鸡仔
 */
public interface EmailService {

    /**
     * 发送邮箱
     * @param email 邮箱
     * @return 是否成功
     */
    public Msg sendEmail(String email);
}
