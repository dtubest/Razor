package com.me.web.servlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: t.ding
 * Date: 13-1-10
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Path {
    /**
     * path 的值不能是"/"或者""，null会被忽略掉
     *
     * @return value的值
     */
    public String value();
}
