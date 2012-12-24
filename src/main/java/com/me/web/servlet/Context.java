package com.me.web.servlet;

import javax.servlet.ServletContext;

/**
 * User: t.ding
 * Date: 12-12-8
 */
public class Context {

    private ServletContext servletContext;

    public ServletContext getServletContext() {
        return servletContext;
    }

    void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
