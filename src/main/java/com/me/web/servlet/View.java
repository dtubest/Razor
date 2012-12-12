package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-11-23
 */
public interface View {
    Resource toResource();

    public void setAttribute(String name, Object value);
}
