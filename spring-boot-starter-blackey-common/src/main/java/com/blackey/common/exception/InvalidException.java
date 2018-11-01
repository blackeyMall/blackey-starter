package com.blackey.common.exception;

import com.blackey.common.result.ResultCode;
import com.blackey.common.result.ResultCodeEnum;

/**
 * 校验异常<p/>
 * Created by Kaven
 * Date: 2018/4/10
 * Desc:
 */
public class InvalidException extends BaseException {
	private static final long serialVersionUID = 8624944628363090977L;

	public InvalidException() {
		super(ResultCodeEnum.INVALID_REQUEST);
	}

	public InvalidException(ResultCode resultCode) {
		super(resultCode);
	}

	public InvalidException(ResultCode resultCode, Throwable cause) {
		super(resultCode, cause);
	}


	public InvalidException(Throwable cause) {
		super(cause);
	}
}