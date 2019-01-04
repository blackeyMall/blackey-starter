package com.blackey.common.exception;

import com.blackey.common.result.ResultCode;

/**
 * shiro 认证授权异常
 * @author wangwei : kaven
 *
 * @date: 2019/1/4 13:46
 **/
public class UnauthorizedException extends BaseException{
    private static final long serialVersionUID = 8624944628773400977L;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message){
        super(message);
    }

    public UnauthorizedException(ResultCode resultCode) {
        super(resultCode);
    }

    public UnauthorizedException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }


    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
}
