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

    private static Map<String, Binder> binderMap = new HashMap<String, Binder>();

    static {
        binderMap.put(String.class.getName(), stringBinder);
        binderMap.put(Integer.class.getName(), integerBinder);
        binderMap.put(Long.class.getName(), longBinder);
        binderMap.put(Float.class.getName(), floatBinder);
        binderMap.put(Double.class.getName(), doubleBinder);
        binderMap.put(Boolean.class.getName(), booleanBinder);
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

    /**
     * 根据类型的全名（java.lang.String）来获取类型binder，如果是不支持的类型，
     * 返回UnsupportedTypeBinder对象，所以这个方法返回值永远不为null
     *
     * @param typeName 类型的全名
     * @return 目标类型binder或者UnsupportedTypeBinder对象
     */
    public static Binder<?> binderOf(String typeName) {
        Binder binder = binderMap.get(typeName);
        if (null != binder)
            return binder;

        return new UnsupportedTypeBinder();
    }

    /**
     * 这个方法用来提供类型转型,由扩展的binder类来实现，将str转成目标类型
     *
     * @param strValue 从request中获取的目标参数的string对象
     * @return 转型后的目标值
     */
    protected abstract T cast(String strValue);
}
