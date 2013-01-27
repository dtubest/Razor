package com.me.web.servlet.binding;

/**
 * User: t.ding
 * Date: 13-1-6
 */
class BooleanBinder extends Binder<Boolean> {
    @Override
    protected Boolean cast(String strValue) {
        return Boolean.valueOf(strValue);
    }
}
