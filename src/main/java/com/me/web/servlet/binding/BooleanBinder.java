package com.me.web.servlet.binding;

import com.me.web.servlet.Mapping;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class BooleanBinder extends Binder<Boolean> {
    @Override
    protected Boolean cast(String strValue) {
        return Boolean.valueOf(strValue);
    }
}
