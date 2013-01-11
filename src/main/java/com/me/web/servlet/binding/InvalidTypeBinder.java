package com.me.web.servlet.binding;

import com.me.web.servlet.FrameworkRequest;
import com.me.web.servlet.HandlerMethod;

/**
 * User: t.ding
 * Date: 13-1-11
 */
public class InvalidTypeBinder extends Binder<Object> {
    @Override
    protected Object cast(String strValue) {
        return null;
    }

    @Override
    protected HandlerMethod getHandler() {
        return null;
    }

    @Override
    public Object get(String paramName, FrameworkRequest request) {
        return null;
    }

    @Override
    public Object get(String paramName, FrameworkRequest request, Object defaultValue) {
        return null;
    }
}
