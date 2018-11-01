package com.blackey.common.exception;

import com.blackey.common.result.ResultCode;
import com.blackey.common.result.ResultCodeEnum;

/**
 * 超时异常<p/>
 * 默认code 504
 * Created by Kaven
 * Date: 2018/4/10
 * Desc:
 */
public class TimeoutException extends BaseException {
	private static final long serialVersionUID = 8620044628365090977L;

	public TimeoutException() {
		super(ResultCodeEnum.SYSTEM_TIMEOUT);
	}

    public TimeoutException(String message){
        super(message);
    }

	public TimeoutException(ResultCode resultCode) {
		super(resultCode);
	}

	public TimeoutException(ResultCode resultCode, Throwable cause) {
		super(resultCode, cause);
	}


	public TimeoutException(Throwable cause) {
		super(cause);
	}
}