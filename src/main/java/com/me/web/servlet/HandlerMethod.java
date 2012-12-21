package com.me.web.servlet;

import java.lang.reflect.Method;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public class HandlerMethod {
    private Class clazz;
    private Method method;

    public HandlerMethod(Class clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
