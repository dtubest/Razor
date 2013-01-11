package com.me.web.servlet.binding;

import com.me.web.servlet.HandlerMethod;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class DoubleBinder extends Binder<Double> {
    final HandlerMethod handler;

    public DoubleBinder(HandlerMethod handler) {
        this.handler = handler;
    }

    @Override
    protected Double cast(String strValue) {
        return Double.valueOf(strValue);
    }

    @Override
    protected HandlerMethod getHandler() {
        return null;
    }
}
