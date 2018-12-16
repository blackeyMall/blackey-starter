package com.blackey.shiro.constants;
import com.blackey.common.result.ResultCode;

/**
 * 菜单enum
 * Created by Kaven
 * Date: 2018/6/4
 */
public enum MenuEnum implements ResultCode {

    CATALOG(0,"目录"),
    MENU(1,"菜单"),
    BUTTON(2,"按钮"),

    VALIDATE_MENU(400010,"上级菜单只能为目录类型"),
    VALIDATE_BUTTON(400010,"上级菜单只能为菜单类型")

    ;


    private int code;
    private String msg;

    MenuEnum(int code, String msg){
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
