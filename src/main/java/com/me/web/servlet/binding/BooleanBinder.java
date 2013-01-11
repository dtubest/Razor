package com.me.web.servlet.binding;

import com.me.web.servlet.HandlerMethod;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class BooleanBinder extends Binder<Boolean> {
    final HandlerMethod handler;

    public BooleanBinder(HandlerMethod handler) {
        this.handler = handler;
    }

    @Override
    protected Boolean cast(String strValue) {
        return Boolean.valueOf(strValue);
    }

    @Override
    protected HandlerMethod getHandler() {
        return handler;
    }
}
