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
public interface IListOutput<From, To extends BaseBo<From>> {

    @SuppressWarnings("unchecked")
    default To createBeanInstance() throws InstantiationException, IllegalAccessException {
        return createBean().newInstance();
    }

    @SuppressWarnings("unchecked")
    default Class<To> createBean() {
        return ((Class<To>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    @SuppressWarnings("unchecked")
    default List<To> fromModel(List<From> fromList) throws Exception {
        if (fromList == null) {
            return Lists.newArrayList();
        }
        List<To> toItems = new ArrayList<>();

        Class<To> toClazz = createBean();

        for (Iterator<From> localIterator = fromList.iterator(); localIterator.hasNext();) {
            Object from = localIterator.next();
            BaseBo<From> to = toClazz.newInstance();
            to.fromModel((From) from);
            toItems.add((To) to);
        }

        return toItems;
    }

    @SuppressWarnings("unchecked")
    default List<To> fromModel(List<From> fromList, To to) throws Exception {
        List<To> toItems = new ArrayList<>();

        if (fromList == null) {
            return toItems;
        }

        Class<To> toClazz = createBean();

        for (Iterator<From> localIterator = fromList.iterator(); localIterator.hasNext();) {
            Object from = localIterator.next();
            BaseBo<To> newTo = (BaseBo<To>) toClazz.newInstance();
            BeanUtil.copyProperties(to,newTo);

            newTo.fromModel((To) from);
            toItems.add((To) newTo);
        }

        return toItems;
    }
}