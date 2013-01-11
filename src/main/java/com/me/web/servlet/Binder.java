package com.me.web.servlet;

import com.me.util.StringUtils;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public abstract class Binder<T> {
    public T get(String paramName, FrameworkRequest request) {
        return get(paramName, request, null);
    }

    public T get(String paramName, FrameworkRequest request, T defaultValue) {
        String parameter;
        int i = getHandler().getMapping().getPathParamIndex(paramName);

        if (-1 != i)
            parameter = request.getRequest().getRequestURI().split("/")[i + 1];
        else
            parameter = request.getRequest().getParameter(paramName);

        if (StringUtils.isEmpty(parameter))
            return defaultValue;

        return cast(parameter);
    }

    protected abstract T cast(String strValue);

    protected abstract HandlerMethod getHandler();
}
