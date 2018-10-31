package com.blackey.common.exception;

import com.blackey.common.result.ResultCode;
import com.blackey.common.result.ResultCodeEnum;

/**
 * 权限异常<p/>
 * 默认code 401
 * Created by Kaven
 * Date: 2018/4/10
 * Desc:
 */
public class PermissionException extends BaseException {
	private static final long serialVersionUID = 8620044635365090977L;

	public PermissionException() {
		super(ResultCodeEnum.UNAUTHORIZED);
	}

	public PermissionException(ResultCode resultCode) {
		super(resultCode);
	}

	public PermissionException(ResultCode resultCode, Throwable cause) {
		super(resultCode, cause);
	}


	public PermissionException(Throwable cause) {
		super(cause);
	}
}