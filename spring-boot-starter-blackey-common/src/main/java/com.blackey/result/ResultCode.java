package com.blackey.result;

/**
 * 返回值
 *
 * @author blackey
 * @date 2018/10/30
 */
public interface ResultCode {
    /**
     * get编码
     * @return 编码值
     */
    int getCode();
    
    /**
     * get话术
     * @return 话术
     */
    String getMsg();
}
