package com.me.web.servlet;

import com.me.web.servlet.annotation.Path;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class Mapping {
    public final Class<?> clazz;
    public final Method method;
    public final String uri;

    private final Pattern pattern;

    public Mapping(Class<?> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
        this.uri = getPath(clazz.getAnnotation(Path.class), clazz.getSimpleName())
                + getPath(method.getAnnotation(Path.class), method.getName());

        pattern = getPattern();
    }

    public boolean match(String uri) {
        return pattern.matcher(uri).matches();
    }

    public String getParam(String paramName, HttpServletRequest request) {
        if (existPathParam(paramName))
            return getPathParam(request, paramName);

        return getQueryParam(request, paramName);
    }

    ////////////////////////////////////////////////////

    private String getQueryParam(HttpServletRequest request, String paramName) {
        return request.getParameter(paramName);
    }

    private String getPathParam(HttpServletRequest request, String paramName) {
        return request.getRequestURI().split(Http.uri_separator)[getPathParamIndex(paramName) + 1];
    }

    private boolean existPathParam(String paramName) {
        return -1 != getPathParamIndex(paramName);
    }

    private int getPathParamIndex(String name) {
        Pattern pattern = Pattern.compile("\\{" + name + "\\}");
        String[] strings = uri.split("/");

        for (int i = 1; i < strings.length; i++) {
            if (pattern.matcher(strings[i]).matches())
                return i - 1;
        }
        return -1;
    }

    private Pattern getPattern() {
        String innerUri = "^" + uri + "$";
        return Pattern.compile(innerUri.replaceAll("\\{[a-zA-Z]+\\}", "[\\\\S]+"));
    }

    private String getPath(Path path, String dName) {
        if (null == path) return Http.uri_separator + dName;
        Http.validatePath(path.value());

        return Http.uri_separator + path.value().replaceAll("^/|/$", "");
    }
}
