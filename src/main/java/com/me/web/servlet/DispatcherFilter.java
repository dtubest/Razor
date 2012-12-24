package com.me.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.me.web.servlet.mapping.AnnotationHandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: t.ding
 * Date: 12-11-23
 */
public class DispatcherFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherFilter.class);

    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";

    private static final String METHOD_TRACE = "TRACE";
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";

    private static final String HEADER_LASTMOD = "Last-Modified";


    private FilterConfig frameworkConfig;
    private HandlerMapping handlerMapping;
    private Dispatcher dispatcher;
    private ViewResolver viewResolver;


    @Override
    public void init(FilterConfig config) throws ServletException {

        /**
         * 资源解析器的初始化
         */
        WebResources.init(config.getServletContext());

        /**
         * 读取配置文件
         */
        String res = "/WEB-INF/" + config.getFilterName() + "-config.json";
        InputStream stream = WebResources.getResourceAsStream(res);
        JSONObject jsonConfig;
        try {
            String text = StringUtils.readAsString(stream);
            jsonConfig = JSON.parseObject(text);

        } catch (IOException e) {
            e.printStackTrace();
        }


        frameworkConfig = config;
        handlerMapping = getHandlerMapping();
        dispatcher = getDispatcher();
        viewResolver = getViewResolver();
        Context.setServletContext(config.getServletContext());
        // todo （如何）注册所有的controller，如何带来开发的高效性
        ControllerManager.registerByPackage("test.controller");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // todo 文件的上传应该如何实现？使用第三方库？自己实现好像不大现实

        // todo 缓存该如何实现对http友好？

        // todo 各种组件应该怎么注册，怎么绑定？毕竟filter可以有多个实例

        // todo action应该如何定义？后缀加.action或者不加，应该怎么处理

        // todo 关于某些路径需要过滤的情况，应该如何做呢？通过配置过滤掉一批请求（可选），如果没有过滤，并且没有找到合适的处理器，就应该chain.doFilter
        // todo 这个可以考虑引入requestEscape的概念

        // todo 还有一大块：参数绑定没做呢。

        // todo mapping这个概念应该有么？应该

        // todo dispatcher这个概念应该有么？。。。

        FrameworkRequest frameworkRequest
                = FrameworkRequest.wrap((HttpServletRequest) request, (HttpServletResponse) response);

        // todo 修改了初始化的策略，但是也引入了另外一个问题，那就是在多线程下面，dispatcher会如何表现呢？
        dispatcher.service(frameworkRequest, handlerMapping, viewResolver);
    }

    private ViewResolver getViewResolver() {
        return new JspViewResolver();
    }

    private Dispatcher getDispatcher() {
        return new DefaultDispatcher();
    }

    protected HandlerMapping getHandlerMapping() {
        return new AnnotationHandlerMapping();
    }

    @Override
    public void destroy() {
    }


    /////////////////////////////////////////////////////////////////

    /**
     * 下面是暂时还没有用到的方法
     */

    private void maybeSetHttpCache(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        String method = httpRequest.getMethod();

        if (method.equals(METHOD_GET)) {
            long lastModified = getLastModified();
            if (lastModified == -1) {
                // servlet doesn't support if-modified-since, no reason
                // to go through further expensive logic
            } else {
                long ifModifiedSince = httpRequest.getDateHeader(HEADER_IFMODSINCE);
                if (ifModifiedSince < (lastModified / 1000 * 1000)) {
                    // If the servlet mod time is later, call doGet()
                    // Round down to the nearest second for a proper compare
                    // A ifModifiedSince of -1 will always be less
                    maybeSetLastModified(httpResponse, lastModified);
                } else {
                    httpResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                }
            }

        } else if (method.equals(METHOD_HEAD)) {
            long lastModified = getLastModified();
            maybeSetLastModified(httpResponse, lastModified);

        } else if (method.equals(METHOD_POST)) {

        } else if (method.equals(METHOD_PUT)) {

        } else if (method.equals(METHOD_DELETE)) {

        } else if (method.equals(METHOD_OPTIONS)) {

        } else if (method.equals(METHOD_TRACE)) {

        }
    }

    private long getLastModified() {
        return -1;
    }

    private void maybeSetLastModified(HttpServletResponse resp,
                                      long lastModified) {
        if (resp.containsHeader(HEADER_LASTMOD))
            return;
        if (lastModified >= 0)
            resp.setDateHeader(HEADER_LASTMOD, lastModified);
    }

}
