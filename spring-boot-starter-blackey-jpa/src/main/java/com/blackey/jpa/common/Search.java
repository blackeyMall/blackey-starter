package com.blackey.jpa.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 查询对象封装
 *
 * @author blackey
 * @date 2018/10/30
 */
public interface Search<T> {

    /**
     * 获取查询组装对象
     * @return
     */
    Specification<T> getSpecification();

    /**
     * 获取起始页码
     * @return
     */
    int getStart();

    /**
     * 设置起始页码
     * @param paramInt
     */
    void setStart(int paramInt);

    /**
     * 获取每页条数
     * @return
     */
    int getSize();

    /**
     * 设置每页条数
     * @param paramInt
     */
    void setSize(int paramInt);

    /**
     * 获取分页
     * @return
     */
    Sort getSort();


    /**
     * 获取分页对象
     * @param paramInteger
     * @return
     */
    PageRequest getPage(Integer paramInteger);

    /**
     * 获取分页对象
     * @return
     */
    PageRequest getPage();
}
