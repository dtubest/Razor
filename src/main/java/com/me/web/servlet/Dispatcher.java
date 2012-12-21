package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-12-10
 */
public interface Dispatcher {
    public void service(FrameworkRequest request, HandlerMapping mapping);
}
