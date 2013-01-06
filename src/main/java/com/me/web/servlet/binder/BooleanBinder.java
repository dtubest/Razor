package com.me.web.servlet.binder;

import com.me.web.servlet.Binder;
import com.me.web.servlet.FrameworkRequest;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class BooleanBinder implements Binder<Boolean> {
    @Override
    public Boolean get(String paramName, FrameworkRequest request) {
        return get(paramName, request, null);
    }

    @Override
    public Boolean get(String paramName, FrameworkRequest request, Boolean defaultValue) {
        Boolean aBoolean = Boolean.valueOf(request.getRequest().getParameter(paramName));
        if (null == aBoolean)
            aBoolean = defaultValue;
        return aBoolean;
    }
}
