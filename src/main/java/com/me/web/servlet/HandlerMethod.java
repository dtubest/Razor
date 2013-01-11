package com.me.web.servlet;

import java.lang.reflect.Method;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public class HandlerMethod {
    private Class clazz;
    private Method method;
    private ControllerManager.Mapping mapping;

    public HandlerMethod(ControllerManager.Mapping mapping) {
        this.clazz = mapping.clazz;
        this.method = mapping.method;
        this.mapping = mapping;
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

    public ControllerManager.Mapping getMapping() {
        return mapping;
    }
}
