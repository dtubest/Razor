package com.me.web.servlet.binding;

import com.me.util.StringUtils;
import com.me.web.servlet.FrameworkRequest;
import com.me.web.servlet.HandlerMethod;

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

    ////////////////////////////////////////

    public static Binder<?> createBinder(String typeName, HandlerMethod handler) {
        if (typeName.equals("java.lang.String")) {
            return new StringBinder(handler);
        } else if (typeName.equals("java.lang.Integer")) {
            return new IntegerBinder(handler);
        } else if (typeName.equals("java.lang.Long")) {
            return new LongBinder(handler);
        } else if (typeName.equals("java.lang.Float")) {
            return new FloatBinder(handler);
        } else if (typeName.equals("java.lang.Double")) {
            return new DoubleBinder(handler);
        } else if (typeName.equals("java.lang.Boolean")) {
            return new BooleanBinder(handler);
        }

        return new InvalidTypeBinder();
    }

    protected abstract T cast(String strValue);

    protected abstract HandlerMethod getHandler();
}
