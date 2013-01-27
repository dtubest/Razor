package com.me.web.servlet.binding;

/**
 * User: t.ding
 * Date: 13-1-6
 */
class DoubleBinder extends Binder<Double> {
    @Override
    protected Double cast(String strValue) {
        return Double.valueOf(strValue);
    }
}
