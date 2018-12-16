package com.blackey.shiro.constants;

import com.blackey.common.result.ResultCode;

/**
 * 角色enum
 * Created by Kaven
 * Date: 2018/6/4
 */
public enum RoleEnum implements ResultCode {

    ROLE_SUPER(1,"超级管理员"),
    ROLE_ADMIN(2,"管理员"),
    ROLE_USER(3,"用户"),
    ROLE_VISITOR(4,"游客");


    private int code;
    private String msg;

    RoleEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
