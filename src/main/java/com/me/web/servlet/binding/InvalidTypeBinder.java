package com.me.web.servlet.binding;

/**
 * User: t.ding
 * Date: 13-1-11
 */
class InvalidTypeBinder extends Binder<Object> {
    @Override
    protected Object cast(String strValue) {
        return null;
    }
}
