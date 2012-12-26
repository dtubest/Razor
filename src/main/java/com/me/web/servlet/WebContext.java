package com.me.web.servlet;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: t.ding
 * Date: 12-12-8
 */
public class WebContext {
    public WebContext(ServletContext context) {
        this.context = context;
    }

    private ServletContext context;

    public ServletContext getServletContext() {
        return context;
    }

    public InputStream getWebResourceAsStream(String res) {
        InputStream result;
        result = context.getResourceAsStream(res);
        if (null == result)
            result = context.getResourceAsStream("/" + res);

        return result;
    }

    public URL getWebResourceAsURL(String res) {
        URL result;
        try {
            result = context.getResource(res);
            if (null == result)
                result = context.getResource("/" + res);
        } catch (MalformedURLException e) {
            return null;
        }

        return result;
    }
}
