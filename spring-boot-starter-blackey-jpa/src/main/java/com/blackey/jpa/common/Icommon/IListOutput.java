package com.blackey.jpa.common.Icommon;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.blackey.jpa.common.BaseBo;
import com.google.common.collect.Lists;

import java.beans.Beans;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * list，page 对象装换
 *
 * @author blackey
 * @date 2018/11/1
 */
public interface IListOutput<Model, Bo extends BaseBo<Model>> {

    @SuppressWarnings("unchecked")
    default Bo createBeanInstance() throws InstantiationException, IllegalAccessException {
        return createBean().newInstance();
    }

    @SuppressWarnings("unchecked")
    default Class<Bo> createBean() {
        return ((Class<Bo>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    @SuppressWarnings("unchecked")
    default List<Bo> fromModel(List<Model> fromList) throws Exception {
        if (fromList == null) {
            return Lists.newArrayList();
        }
        List<Bo> toItems = new ArrayList<>();

        Class<Bo> toClazz = createBean();

        for (Iterator<Model> localIterator = fromList.iterator(); localIterator.hasNext();) {
            Object from = localIterator.next();
            BaseBo<Model> to = toClazz.newInstance();
            to.fromModel((Model) from);
            toItems.add((Bo) to);
        }

        return toItems;
    }

    @SuppressWarnings("unchecked")
    default List<Bo> fromModel(List<Model> fromList, Bo to) throws Exception {
        List<Bo> toItems = new ArrayList<>();

        if (fromList == null) {
            return toItems;
        }

        Class<Bo> toClazz = createBean();

        for (Iterator<Model> localIterator = fromList.iterator(); localIterator.hasNext();) {
            Object from = localIterator.next();
            BaseBo<Bo> newBo = (BaseBo<Bo>) toClazz.newInstance();
            BeanUtil.copyProperties(to,newBo);

            newBo.fromModel((Bo) from);
            toItems.add((Bo) newBo);
        }

        return toItems;
    }
}