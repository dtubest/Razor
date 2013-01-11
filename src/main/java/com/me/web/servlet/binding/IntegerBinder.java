package com.me.web.servlet.binding;

import com.me.web.servlet.HandlerMethod;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class IntegerBinder extends Binder<Integer> {
    final HandlerMethod handler;

    public IntegerBinder(HandlerMethod handler) {
        this.handler = handler;
    }

    @Override
    protected Integer cast(String strValue) {
        return Integer.valueOf(strValue);
    }

    @Override
    protected HandlerMethod getHandler() {
        return handler;
    }
}
