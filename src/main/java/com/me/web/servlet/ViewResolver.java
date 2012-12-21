package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public interface ViewResolver {
    public void render(FrameworkRequest request, String view);
}
