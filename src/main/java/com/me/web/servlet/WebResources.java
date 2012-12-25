package com.me.web.servlet;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: t.ding
 * Date: 12-12-24
 */
public class WebResources {
    private static final ClassLoader loader = WebResources.class.getClassLoader();
    private static ServletContext context;
    private static boolean initialized;


    public static InputStream getResourceAsStream(String res) {
        InputStream result;
        result = loader.getResourceAsStream(res);
        if (null == result) result = context.getResourceAsStream(res);
        return result;
    }

    public static URL getResource(String res) {
        URL result;
        result = loader.getResource(res);

        if (null == result)
            try {
                result = context.getResource(res);
            } catch (MalformedURLException e) {
                return null;
            }

        return result;
    }

    static void init(ServletContext context) {
        if (initialized) return;
        WebResources.context = context;

        initialized = true;
    }
}
