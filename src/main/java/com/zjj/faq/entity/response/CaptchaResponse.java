package com.zjj.faq.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 阿呆的小鸡仔
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CaptchaResponse {
    /**
     * 该图片的uuid，登陆时按照此id,得到图片进行验证
     */
    private String uuid;
    /**
     * 图形验证码的src,前面将此作为ima标签的src值就可以显示图片
     */
    private String image;
}