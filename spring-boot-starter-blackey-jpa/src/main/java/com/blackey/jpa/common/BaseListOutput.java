package com.blackey.jpa.common;

import com.blackey.jpa.common.Icommon.IListOutput;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * list page 对象输出
 *
 * @author blackey
 * @date 2018/11/1
 */
public abstract class BaseListOutput<Model, Bo extends BaseBo<Model>> implements IListOutput<Model, Bo> {
    List<Bo> list;
    Page<Bo> page;
    private Logger log = LoggerFactory.getLogger(BaseListOutput.class);

    @SuppressWarnings("unchecked")
    public BaseListOutput(Object from) {
        try {
            if (from instanceof List) {
                this.list = fromModel((List<Model>)from);
            }

            if (from instanceof Page)
                fromModel((Page<Model>) from);

        } catch (Exception ex) {
            this.log.error(ExceptionUtils.getStackTrace(ex));
        }
    }

    public BaseListOutput(Object from, Bo to) {
        try {
            if (from instanceof List) {
                this.list = fromModel((List<Model>) from, to);
            }

            if (from instanceof Page)
                fromModel((Page<Model>) from, to);
        } catch (Exception ex) {
            this.log.error(ExceptionUtils.getStackTrace(ex));
        }
    }

    public void fromModel(Page<Model> from) throws Exception {
        this.list = fromModel(from.getContent());
        this.page = new PageImpl<Bo>(this.list, new PageRequest(from.getNumber(), from.getSize()), from.getTotalElements());
    }

    public void fromModel(Page<Model> from, Bo to) throws Exception {
        this.list = fromModel(from.getContent(), to);
        this.page = new PageImpl<Bo>(this.list, new PageRequest(from.getNumber(), from.getSize()), from.getTotalElements());
    }

    public Page<Bo> getPage() {
        return this.page;
    }

    public List<Bo> getList() {
        return this.list;
    }
}
