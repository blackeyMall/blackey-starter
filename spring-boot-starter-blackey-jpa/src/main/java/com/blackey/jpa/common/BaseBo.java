package com.blackey.jpa.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.Beans;

/**
 * 输出
 *
 * @author blackey
 * @date 2018/10/30
 */
public abstract class BaseBo<T> {

    private Logger log = LoggerFactory.getLogger(BaseBo.class);

    protected abstract void processBean(T paramT);

    public void fromModel(T from) {
        this.log.debug("From Model [{}] content [{}]", from.getClass().getName(), from.toString());
        try {
            BeanUtils.copyProperties(from, this);
            processBean(from);
        } catch (Exception ex) {
            this.log.error("Model reflection copy (all) error [{}] ! ", ExceptionUtils.getStackTrace(ex));
        }

        this.log.debug("To Model [{}] after process content [{}]", super.getClass().getName(), super.toString());
    }

}
