package com.blackey.auth.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blackey.auth.domain.JWTInfoForm;
import com.blackey.common.result.Result;
import com.blackey.common.result.ResultCodeEnum;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * jwt util
 * @author wangwei : kaven
 *
 * @date: 2019/1/3 16:09
 **/
public class JWTUtil {

    private static final String USER_NAME = "userName";


    /**
     * 生成签名,默认5min后过期
     * @param jwtInfoForm
     * @return
     */
    public static String generToken(JWTInfoForm jwtInfoForm){
        try {
            Date date = new Date(System.currentTimeMillis()+jwtInfoForm.getExpireTime());
            Algorithm algorithm = Algorithm.HMAC256(jwtInfoForm.getSecret());
            // 附带username信息
            return JWT.create()
                    .withSubject(jwtInfoForm.getUserId())
                    .withClaim(USER_NAME, jwtInfoForm.getUserName())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    /**
     * 解析token
     * @param token
     * @param jwtInfoForm
     * @return
     */
    public static DecodedJWT getDecodedJWT(String token, JWTInfoForm jwtInfoForm){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtInfoForm.getSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject(jwtInfoForm.getUserId())
                    .withClaim(USER_NAME,jwtInfoForm.getUserName())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param jwtInfoForm
     * @return 是否正确
     */
    public static Result verifyToken(String token, JWTInfoForm jwtInfoForm) {
        DecodedJWT jwt = getDecodedJWT(token, jwtInfoForm);
        if(null == jwt){
            return new Result(ResultCodeEnum.INVALID_REQUEST);
        }
        String subject = jwt.getSubject();
        String userName = jwt.getClaim(USER_NAME).asString();
        Date expiresAt = jwt.getExpiresAt();

//        if(expiresAt.before(new Date())){
//            return new Result(ResultCodeEnum.TOKEN_TIMEOUT_ERROR);
//        }

        if(!jwtInfoForm.getUserId().equals(subject)
                || !jwtInfoForm.getUserName().equals(userName)){
            return new Result(ResultCodeEnum.INVALID_REQUEST);
        }
        return new Result(ResultCodeEnum.SUCCESS);
    }

    /**
     * 校验token是否过期
     * @param token 密钥
     * @return 是否过期
     */
    public static boolean verifyExpireTime(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expiresAt = jwt.getExpiresAt();
            return expiresAt.after(new Date());
        } catch (JWTDecodeException e) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(USER_NAME).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的subject
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的过期时间
     */
    public static Date getExpireAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
