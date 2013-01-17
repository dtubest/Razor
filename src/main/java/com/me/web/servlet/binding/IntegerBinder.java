package com.me.web.servlet.binding;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class IntegerBinder extends Binder<Integer> {
    @Override
    protected Integer cast(String strValue) {
        return Integer.valueOf(strValue);
    }
}
