package com.me.web.servlet.binding;

import com.me.web.servlet.Mapping;

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
