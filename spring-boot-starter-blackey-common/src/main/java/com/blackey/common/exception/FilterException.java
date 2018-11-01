package com.blackey.common.exception;

import com.blackey.common.result.ResultCode;
import com.blackey.common.result.ResultCodeEnum;

/**
 * filter中抛出的异常<p/>
 * 默认code 500
 * Created by Kaven
 * Date: 2018/4/10
 * Desc:
 */
public class FilterException extends BaseException {
	private static final long serialVersionUID = 8690044628365090977L;

	public FilterException() {
		super(ResultCodeEnum.SYSTEM_TIMEOUT);
	}

	public FilterException(ResultCode resultCode) {
		super(resultCode);
	}

	public FilterException(ResultCode resultCode, Throwable cause) {
		super(resultCode, cause);
	}


	public FilterException(Throwable cause) {
		super(cause);
	}
}