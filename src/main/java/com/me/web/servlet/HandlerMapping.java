package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-11-23
 */
public interface HandlerMapping {
    Handler getHandler(FrameworkRequest request);

    Handler getHandler(FrameworkRequest request, String uri);
}
