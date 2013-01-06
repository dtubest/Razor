package com.me.web.servlet.binder;

import com.me.web.servlet.Binder;
import com.me.web.servlet.FrameworkRequest;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class SimpleBeanBinder implements Binder<Object> {

    @Override
    public Object get(String paramName, FrameworkRequest request) {
        return null;
    }

    @Override
    public Object get(String paramName, FrameworkRequest request, Object defaultValue) {
        return null;
    }
}
