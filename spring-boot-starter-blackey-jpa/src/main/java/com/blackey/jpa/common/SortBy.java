package com.blackey.jpa.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 排序枚举类
 *
 * @author blackey
 * @date 2018/10/30
 */
public enum  SortBy {

    /**
     * 排序字段
     */
    timeCreatedDesc, timeCreatedAsc, timeModifiedDesc, timeModifiedAsc;

    private String value;
    private String name;

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public static SortBy formatEnum(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return valueOf(key);
        } catch (Exception e) {
        }
        return timeCreatedDesc;
    }
}
