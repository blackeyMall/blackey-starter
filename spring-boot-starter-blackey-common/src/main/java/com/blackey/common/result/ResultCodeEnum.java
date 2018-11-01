package com.blackey.common.result;

/**
 * 常用返回编码枚举.
 *
 * @author CUIAIWEN118.
 * @date 2017/12/12.
 */
public enum ResultCodeEnum implements ResultCode {
    /**
     * 成功的返回值
     */
    SUCCESS(200, "操作成功！"),
    
    /**
     * 参数校验失败的返回值
     */
    INVALID_REQUEST(400, "非法参数！"),
    
    /**
     * 权限不足的返回值
     */
    UNAUTHORIZED(401, "非法访问！"),
    
    /**
     * 找不到记录的返回值
     */
    NOT_FOUND(404, "记录不存在！"),
    
    /**
     * 系统异常的返回值
     */
    SYSTEM_ERROR(500, "系统异常，请稍后再试！"),
    /**
     *系统超时
     */
    SYSTEM_TIMEOUT(504,"系统超时，请重试！"),
    
    /**
     * session超时的返回值
     */
    SESSION_TIMEOUT(504, "抱歉，当前微信授权时间已失效，请重新进入。"),
    /**
     * 解密失败的返回值
     */
    DECRYPT_ERROR(400001, "解密失败。"),
    /**
     * s签名失败的返回值
     */
    SIGN_ERROR(400002, "签名失败。"),
    /**
     * s签名失败的返回值
     */
    DONT_SPEAK_ERROR(407, "您已被禁言。"),
    /**
     * s签名失败的返回值
     */
    WXMPTOKEN_ERROR(406, "拉取当前用户信息失败，请确保在微信端使用。"),


    ;
    private int code;
    private String msg;
    
    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
