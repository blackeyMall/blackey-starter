package com.blackey.common.exception;

import com.blackey.common.result.ResultCode;
import com.blackey.common.result.ResultCodeEnum;

/**
 * 异常父类，所有异常均可继承该类
 * Created by Kaven
 * Date: 2018/4/10
 * Desc:
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private int errorCode;

    public BaseException() {
        this(ResultCodeEnum.SYSTEM_ERROR);
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.errorCode = resultCode.getCode();
    }

    public BaseException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMsg(), cause);
        this.errorCode = resultCode.getCode();
    }

    public BaseException(Throwable cause) {
        this(ResultCodeEnum.SYSTEM_ERROR,cause);
    }

    public BaseException(ResultCode resultCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(resultCode.getMsg(), cause, enableSuppression, writableStackTrace);
        this.errorCode = resultCode.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
