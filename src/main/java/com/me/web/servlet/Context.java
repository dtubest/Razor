package com.me.web.servlet;

import javax.servlet.ServletContext;

/**
 * User: t.ding
 * Date: 12-12-8
 */
@Deprecated
public class Context {

    // todo 因为使用static的方式是整个应用共用静态域，会无法区分filter多个实例的具体区别，所以这个类应该被废弃或者转换成非静态的方式来使用。

    private static ServletContext servletContext;

    @Deprecated
    public static ServletContext getServletContext() {
        return servletContext;
    }

    @Deprecated
    static void setServletContext(ServletContext servletContext) {
        Context.servletContext = servletContext;
    }
}
