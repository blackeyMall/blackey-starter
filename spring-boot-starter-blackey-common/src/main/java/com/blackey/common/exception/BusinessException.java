package com.blackey.common.exception;

import com.blackey.common.result.ResultCode;

/**
 * 业务异常<p/>
 * Created by Kaven
 * Date: 2018/4/10
 * Desc:
 */
public class BusinessException extends BaseException {
	private static final long serialVersionUID = 8624944628363400977L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message){
	    super(message);
    }

	public BusinessException(ResultCode resultCode) {
		super(resultCode);
	}

	public BusinessException(ResultCode resultCode, Throwable cause) {
		super(resultCode, cause);
	}


	public BusinessException(Throwable cause) {
		super(cause);
	}
}