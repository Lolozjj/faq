package com.zjj.faq.service;

import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.entity.response.CaptchaResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoginService {
    public CaptchaResponse getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public Msg login(String name, String password, String captchaUid, String captchaText);
}
