package com.blackey.auth.service;

import com.blackey.auth.domain.JWTInfoForm;
import com.blackey.common.result.Result;

import java.util.Date;

/**
 * jwt auth service
 * @author wangwei : kaven
 *
 * @date: 2019/1/7 15:05
 **/
public interface AuthService {

    /**
     * 生成token
     * @param form
     * @return
     */
    String generToken(JWTInfoForm form);

    /**
     * 校验token
     * @param token
     * @param jwtInfoForm
     * @return
     */
    Result verifyToken(String token, JWTInfoForm jwtInfoForm);

    /**
     * 获取用户名
     * @param token
     * @return
     */
    String getUserName(String token);

    /**
     * 获取 subject  即userid
     * @param token
     * @return
     */
    String getSubject(String token);

    /**
     * 获取token的过期时间
     * @param token
     * @return
     */
    Date getExpireAt(String token);
}
