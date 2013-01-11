package com.me.web.servlet.binding;

import com.me.web.servlet.HandlerMethod;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class FloatBinder extends Binder<Float> {
    private final HandlerMethod handler;

    FloatBinder(HandlerMethod handler) {
        this.handler = handler;
    }

    @Override
    protected Float cast(String strValue) {
        return Float.valueOf(strValue);
    }

    @Override
    protected HandlerMethod getHandler() {
        return handler;
    }
}
