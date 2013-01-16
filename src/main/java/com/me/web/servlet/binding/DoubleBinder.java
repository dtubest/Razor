package com.me.web.servlet.binding;

import com.me.web.servlet.Mapping;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class DoubleBinder extends Binder<Double> {
    @Override
    protected Double cast(String strValue) {
        return Double.valueOf(strValue);
    }
}
