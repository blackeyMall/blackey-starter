package com.blackey.jpa.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * TODO
 *
 * @author blackey
 * @date 2018/10/30
 */
public abstract class BaseForm<T> implements Form<T> {

    private Logger log;
    private String id;
    private boolean updateTimeModified;

    public BaseForm() {
        this.log = LoggerFactory.getLogger(BaseForm.class);

        this.updateTimeModified = false;
    }

    protected abstract void processBean(T paramT);

    @Override
    public String getId() {
        return StringUtils.trimToNull(this.id);
    }

    @Override
    public void setId(String id) {
        this.id = StringUtils.trimToNull(id);
    }

    public T toModel() {
        return toModel(false);
    }

    @SuppressWarnings("unchecked")
    public T toModel(boolean nullAware) {
        Object to;
        try {
            to = createBeanInstance();
            toModel((T) to, nullAware);
            return (T) to;
        } catch (Exception ex) {
            this.log.error("toModel error [{}]", ExceptionUtils.getStackTrace(ex));
        }
        return null;
    }

    @Override
    public void toModel(T to, boolean nullAware) {
        this.log.debug("From Model [{}] content [{}]", super.getClass().getName(), super.toString());
        try {
            if (nullAware) {
                //todo 空值不拷贝
//                Beans.copyNullAware(this, to);
            } else {
                BeanUtils.copyProperties(this, to);
            }

            processBean(to);
        } catch (Exception ex) {
            this.log.error("Model reflection copy (nullAware:{}) error [{}] ! ", Boolean.valueOf(nullAware),
                    ExceptionUtils.getStackTrace(ex));
        }

        this.log.debug("To Model [{}] after process content [{}]", to.getClass().getName(), to.toString());
    }

    @Override
    public boolean isUpdateTimeModified() {
        return this.updateTimeModified;
    }

    public void setUpdateTimeModified(boolean updateTimeModified) {
        this.updateTimeModified = updateTimeModified;
    }
}
