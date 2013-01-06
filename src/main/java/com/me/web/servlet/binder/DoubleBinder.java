package com.me.web.servlet.binder;

import com.me.web.servlet.Binder;
import com.me.web.servlet.FrameworkRequest;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class DoubleBinder implements Binder<Double> {

    @Override
    public Double get(String paramName, FrameworkRequest request) {
        return null;
    }

    @Override
    public Double get(String paramName, FrameworkRequest request, Double defaultValue) {
        return null;
    }
}
