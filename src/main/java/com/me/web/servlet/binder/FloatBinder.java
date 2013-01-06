package com.me.web.servlet.binder;

import com.me.web.servlet.Binder;
import com.me.web.servlet.FrameworkRequest;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class FloatBinder implements Binder<Float> {
    @Override
    public Float get(String paramName, FrameworkRequest request) {
        return null;
    }

    @Override
    public Float get(String paramName, FrameworkRequest request, Float defaultValue) {
        return null;
    }
}
