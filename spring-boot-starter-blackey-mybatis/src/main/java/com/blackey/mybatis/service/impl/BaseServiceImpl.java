package com.blackey.mybatis.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackey.mybatis.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  Kaven
 * @date: 2018/4/8
 * Desc:
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
