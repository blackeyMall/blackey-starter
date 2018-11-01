package com.blackey.jpa.common.Icommon;

import java.lang.reflect.ParameterizedType;

/**
 * 入参封装
 *
 * @author blackey
 * @date 2018/10/30
 */
public interface IForm<T> {


    /**
     * 创建泛型实例
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    default T createBeanInstance()
            throws InstantiationException, IllegalAccessException
    {
        return createBean().newInstance();
    }

    /**
     * 获取超类的泛型类型
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    default Class<T> createBean() {
        return ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    String getId();

    void setId(String paramString);

    void toModel(T paramT, boolean paramBoolean);

    boolean isUpdateTimeModified();
}
