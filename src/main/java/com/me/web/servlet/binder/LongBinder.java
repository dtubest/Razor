package com.me.web.servlet.binder;

import com.me.web.servlet.Binder;
import com.me.web.servlet.FrameworkRequest;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class LongBinder implements Binder<Long> {

    @Override
    public Long get(String paramName, FrameworkRequest request) {
        return null;
    }

    @Override
    public Long get(String paramName, FrameworkRequest request, Long defaultValue) {
        return null;
    }
}
