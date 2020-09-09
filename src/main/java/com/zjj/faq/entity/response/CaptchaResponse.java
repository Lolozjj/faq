package com.zjj.faq.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * 登陆的图形验证码的返回
 */
public class CaptchaResponse {
    private String uuid; //该图片的uuid，登陆时按照此id,得到图片进行验证
    private String image;//图形验证码的src,前面将此作为ima标签的src值就可以显示图片
}