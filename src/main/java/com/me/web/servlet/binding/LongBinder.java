package com.me.web.servlet.binding;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class LongBinder extends Binder<Long> {
    @Override
    protected Long cast(String strValue) {
        return Long.valueOf(strValue);
    }
}
