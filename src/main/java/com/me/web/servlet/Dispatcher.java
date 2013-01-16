package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-12-10
 */
public interface Dispatcher {
    public Result service(Mapping mapping, FrameworkRequest request);
}
