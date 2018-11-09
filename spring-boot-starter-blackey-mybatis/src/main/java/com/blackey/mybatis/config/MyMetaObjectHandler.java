package com.blackey.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充字段
 *
 * @author blackey
 * @date 2018/11/8
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ....");
        this.setFieldValByName("createdDate", new Date(), metaObject);
        this.setFieldValByName("updatedDate", new Date(), metaObject);
        this.setFieldValByName("createdBy", "system", metaObject);
        this.setFieldValByName("updatedBy", "system", metaObject);
        this.setFieldValByName("isDeleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ....");
        this.setFieldValByName("updatedDate", new Date(), metaObject);
        this.setFieldValByName("updatedBy", "system", metaObject);
    }
}