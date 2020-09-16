package com.zjj.faq.batis.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author chen
 * @date 2019/7/23
 * @email 15218979950@163.com
 * @description  JWT工具类
 */
public class JwtUtil {
    /**
     * token 过期时间
     */
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;
    /**
     * 密钥
     */
    private static final String SECRET = "zheng";

    /**
     * 生成 token
     */
    public static String createToken(String account) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("account", account)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     */
    public static boolean verify(String token, String account) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("account", account)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     */
    public static String getAccount(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("account").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
