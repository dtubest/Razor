package com.me.web.servlet;

import java.io.InputStream;
import java.net.URL;

/**
 * User: t.ding
 * Date: 12-12-24
 */
public class WebResources {
    // todo 应该封装对多ClassLoader的支持
    private static ClassLoader loader;
    private static WebContext context;

    static {
        loader = WebResources.class.getClassLoader();
    }

    public static InputStream getResourceAsStream(String res) {
        InputStream result;
        result = loader.getResourceAsStream(res);
        if (null == result)
            result = loader.getResourceAsStream("/" + res);

        if (null == result)
            result = context.getWebResourceAsStream(res);

        return result;
    }

    public static URL getResourceAsURL(String res) {
        URL result;
        result = loader.getResource(res);
        if (null == result)
            result = loader.getResource("/" + res);

        if (null == result && null != context)
            result = context.getWebResourceAsURL(res);
        return result;
    }

    static void init(WebContext webContext) {
        context = webContext;
    }
}
