package com.blackey.auth.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * jwt info form 信息
 * @author : kaven
 *
 * @date: 2019/1/3 17:14
 **/
@Setter
@Getter
public class JWTInfoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private long expireTime = 5*60*1000;
    private String secret;
}
