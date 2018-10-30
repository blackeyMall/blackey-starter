package com.blackey.jpa.common;

import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * 基础查询对象
 *
 * @author blackey
 * @date 2018/10/30
 */
public abstract class BaseSearch<T> implements Serializable, Search<T> {

    private static final long serialVersionUID = 7954737573502075242L;
    protected int start;
    protected int size;
    protected String keywords;

    protected SortBy sortby;

    public BaseSearch() {
        this.start = 0;

        this.size = 20;

        this.sortby = SortBy.timeCreatedDesc;
    }

    @Override
    public int getStart() {
        return this.start;
    }

    @Override
    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public Sort getSort() {
        if (this.sortby.equals(SortBy.timeCreatedDesc)) {
            return new Sort(Sort.Direction.DESC, "timeCreated");
        }

        if (this.sortby.equals(SortBy.timeCreatedAsc)) {
            return new Sort(Sort.Direction.ASC, "timeCreated");
        }
        if (this.sortby.equals(SortBy.timeModifiedDesc)) {
            return new Sort(Sort.Direction.DESC, "timeModified");
        }
        if (this.sortby.equals(SortBy.timeModifiedAsc)) {
            return new Sort(Sort.Direction.ASC, "timeModified");
        }

        return new Sort(Sort.Direction.DESC, "timeCreated");
    }


    public SortBy getSortby() {
        return this.sortby;
    }

    public void setSortby(String sortby) {
        this.sortby = SortBy.formatEnum(sortby);
    }
}
