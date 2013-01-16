package com.me.web.servlet.binding;

import com.me.web.servlet.Mapping;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class FloatBinder extends Binder<Float> {
    @Override
    protected Float cast(String strValue) {
        return Float.valueOf(strValue);
    }
}
