package com.me.web.servlet.binder;

import com.me.web.servlet.Binder;
import com.me.web.servlet.FrameworkRequest;
import com.me.web.servlet.StringUtils;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public class StringBinder implements Binder<String> {
    @Override
    public String get(String paramName, FrameworkRequest request) {
        return get(paramName, request, null);
    }

    @Override
    public String get(String paramName, FrameworkRequest request, String defaultValue) {
        String parameter = request.getRequest().getParameter(paramName);
        if (StringUtils.isEmpty(parameter))
            parameter = defaultValue;
        return parameter;
    }
}
