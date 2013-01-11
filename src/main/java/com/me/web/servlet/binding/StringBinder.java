package com.me.web.servlet.binding;

import com.me.web.servlet.Binder;
import com.me.web.servlet.HandlerMethod;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class StringBinder extends Binder<String> {
    private final HandlerMethod handler;

    public StringBinder(HandlerMethod handler) {
        this.handler = handler;
    }

    @Override
    protected String cast(String strValue) {
        return strValue;
    }

    @Override
    protected HandlerMethod getHandler() {
        return handler;
    }
}
