package com.me.web.servlet.binding;

import com.me.web.servlet.Mapping;

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
