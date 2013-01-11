package com.me.web.servlet.binding;

import com.me.web.servlet.Binder;
import com.me.web.servlet.HandlerMethod;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class LongBinder extends Binder<Long> {
    private final HandlerMethod handler;

    public LongBinder(HandlerMethod handler) {
        this.handler = handler;
    }

    @Override
    protected Long cast(String strValue) {
        return Long.valueOf(strValue);
    }

    @Override
    protected HandlerMethod getHandler() {
        return handler;
    }
}
