package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-12-10
 */
public interface RouteStrategy {
    public Handler acceptForHandler(String uri);
}