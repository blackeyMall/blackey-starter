package com.blackey.common.annotation;


import com.blackey.common.enums.SensitiveTypeEnum;

import java.lang.annotation.*;

/**
 * 自定义脱敏注解
 * Created by Kaven
 * Date: 2018/5/21
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitized {

    /*脱敏类型(规则)*/
    SensitiveTypeEnum type();

    /*判断注解是否生效的方法*/
    String isEffictiveMethod() default "";

}