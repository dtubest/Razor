package com.me.web.servlet.binding;

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
