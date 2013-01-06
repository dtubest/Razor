package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 13-1-6
 */
public interface Binder<T> {
    T get(String paramName, FrameworkRequest request);

    T get(String paramName, FrameworkRequest request, T defaultValue);
}
