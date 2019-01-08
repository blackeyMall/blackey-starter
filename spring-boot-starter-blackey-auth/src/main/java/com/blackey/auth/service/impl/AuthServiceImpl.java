package com.blackey.auth.service.impl;

import com.blackey.auth.domain.JWTInfoForm;
import com.blackey.auth.service.AuthService;
import com.blackey.auth.util.JWTUtil;
import com.blackey.common.result.Result;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * jwt auth service
 * @author wangwei : kaven
 *
 * @date: 2019/1/7 15:05
 **/
@Service
public class AuthServiceImpl implements AuthService {
    /**
     * 生成token
     *
     * @param form
     * @return
     */
    @Override
    public String generToken(JWTInfoForm form) {
        return JWTUtil.generToken(form);
    }

    /**
     * 校验token
     *
     * @param token
     * @param jwtInfoForm
     * @return
     */
    @Override
    public Result verifyToken(String token, JWTInfoForm jwtInfoForm) {
        return JWTUtil.verifyToken(token,jwtInfoForm);
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    @Override
    public String getUserName(String token) {
        return JWTUtil.getUsername(token);
    }

    /**
     * 获取 subject  即userid
     *
     * @param token
     * @return
     */
    @Override
    public String getSubject(String token) {
        return JWTUtil.getUserId(token);
    }

    /**
     * 获取token的过期时间
     *
     * @param token
     * @return
     */
    @Override
    public Date getExpireAt(String token) {
        return JWTUtil.getExpireAt(token);
    }
}
