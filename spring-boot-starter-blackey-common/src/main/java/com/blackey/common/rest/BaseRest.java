package com.blackey.common.rest;

import com.blackey.common.result.Result;
import com.blackey.common.result.ResultCode;
import com.blackey.common.result.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * rest 超级父类
 * 所有的rest都需要继承该类，里面包含logger和常用返回值
 * @author  Kaven
 * @date 2018/6/4
 */
public abstract class BaseRest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回成功消息，{"code":200,"message":"操作成功！","data":null}
     * @return Result
     */
    protected Result success(){
        return success(null);
    }

    /**
     * 返回成功消息，{"code":200,"message":"操作成功！","data":object}
     * @param object 具体返回的数据
     * @return Result
     */
    protected Result success(Object object){
        return new Result<>(ResultCodeEnum.SUCCESS,object);
    }

    /**
     * 返回失败消息，{"code":500,"message":"系统异常，请稍后再试！","data":null}
     * @return Result
     */
    protected Result failure(){
        return failure(null);
    }

    /**
     * 返回失败消息，{"code":500,"message":"系统异常，请稍后再试！","data":object}
     * @param object 具体返回的数据
     * @return Result
     */
    protected Result failure(Object object){
        return new Result<>(ResultCodeEnum.SYSTEM_ERROR,object);
    }

    /**
     * 返回失败消息，自定义ResultCode和data
     * @param resultCode 自定义ResultCode
     * @param object 具体返回的数据
     * @return Result
     */
    protected Result failure(ResultCode resultCode, Object object){
        return new Result<>(resultCode,object);
    }

    /**
     * 返回失败消息，自定义ResultCode
     * @param resultCode 自定义ResultCode
     * @return Result
     */
    protected Result failure(ResultCode resultCode){
        return new Result<>(resultCode);
    }
}
