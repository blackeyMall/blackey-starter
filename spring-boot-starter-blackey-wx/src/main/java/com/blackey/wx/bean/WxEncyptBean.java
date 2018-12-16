package com.blackey.wx.bean;

import lombok.Data;

/**
 * 微信加密字符串
 *
 * @author blackey
 * @date 2018/12/10
 */
@Data
public class WxEncyptBean {

    private String iv;

    private String encryptData;

    private String wxSessionKey;



}
