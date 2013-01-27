package com.me.web.servlet.binding;

/**
 * User: t.ding
 * Date: 13-1-6
 */
class StringBinder extends Binder<String> {
    @Override
    protected String cast(String strValue) {
        return strValue;
    }
}
