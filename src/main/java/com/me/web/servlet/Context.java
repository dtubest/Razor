package com.me.web.servlet;

import javax.servlet.ServletContext;

/**
 * User: t.ding
 * Date: 12-12-8
 */
public class Context {
    private static ServletContext servletContext;

    public static ServletContext getServletContext() {
        return servletContext;
    }

    static void setServletContext(ServletContext servletContext) {
        Context.servletContext = servletContext;
    }
}
