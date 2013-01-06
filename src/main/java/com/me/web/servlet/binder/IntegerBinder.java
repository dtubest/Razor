package com.me.web.servlet.binder;

import com.me.web.servlet.Binder;
import com.me.web.servlet.FrameworkRequest;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class IntegerBinder implements Binder<Integer> {
    @Override
    public Integer get(String paramName, FrameworkRequest request) {
        return null;
    }

    @Override
    public Integer get(String paramName, FrameworkRequest request, Integer defaultValue) {
        return null;
    }
}
