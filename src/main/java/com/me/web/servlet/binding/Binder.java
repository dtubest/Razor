package com.me.web.servlet.binding;

import com.me.util.StringUtils;
import com.me.web.servlet.Http;
import com.me.web.servlet.Mapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public abstract class Binder<T> {
    public static final StringBinder stringBinder = new StringBinder();
    public static final IntegerBinder integerBinder = new IntegerBinder();
    public static final LongBinder longBinder = new LongBinder();
    public static final FloatBinder floatBinder = new FloatBinder();
    public static final DoubleBinder doubleBinder = new DoubleBinder();
    public static final BooleanBinder booleanBinder = new BooleanBinder();

    private static Map<Class, Binder> binderMap = new HashMap<Class, Binder>();

    static {
        binderMap.put(String.class, stringBinder);
        binderMap.put(Integer.class, integerBinder);
        binderMap.put(Long.class, longBinder);
        binderMap.put(Float.class, floatBinder);
        binderMap.put(Double.class, doubleBinder);
        binderMap.put(Boolean.class, booleanBinder);
    }

    public T get(String paramName, HttpServletRequest request, Mapping mapping) {
        return get(paramName, request, mapping, null);
    }

    public T get(String paramName, HttpServletRequest request, Mapping mapping, T defaultValue) {
        String parameter;
        int i = mapping.getPathParamIndex(paramName);

        if (-1 != i)
            parameter = request.getRequestURI().split(Http.uri_separator)[i + 1];
        else
            parameter = request.getParameter(paramName);

        if (StringUtils.isEmpty(parameter))
            return defaultValue;

        return cast(parameter);
    }

    public static Binder<?> binderOf(String typeName) {
        Binder binder;
        try {
            binder = binderMap.get(Class.forName(typeName));
        } catch (ClassNotFoundException e) {
            binder = null;
        }
        if (null != binder)
            return binder;

        return new InvalidTypeBinder();
    }

    protected abstract T cast(String strValue);
}
