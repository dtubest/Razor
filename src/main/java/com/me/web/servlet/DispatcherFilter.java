package com.me.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

    private static final String config_file = "config-file";


    private FilterConfig frameworkConfig;
    private HandlerMapping handlerMapping;
    private Dispatcher dispatcher;
    private ViewResolver viewResolver;
    private RequestEscape[] escapes = new RequestEscape[]{};

    private String basePackage;

    private Context context = new Context();

    @Override
    public void init(FilterConfig config) throws ServletException {
        /**
         * 资源解析器的初始化
         */
        WebResources.init(config.getServletContext());

        frameworkConfig = config;
        context.setServletContext(config.getServletContext());

        initStrategies(config);
        registerControllers(config);
    }

    private void registerControllers(FilterConfig config) {
        basePackage = config.getInitParameter("controller-package");
        if (StringUtils.isEmpty(basePackage)) {
            throw new FrameworkInitException();
        }
        ControllerManager.registerByPackage(basePackage);
    }

    private JSONObject jsonConfig;

    private void initStrategies(FilterConfig config) {
        /**
         * 读取配置文件:先判断是否指定了自定义的配置文件，否则检查默认文件，如果两者都没有，使用默认配置
         */
        readConfigFile(config);

        try {
            initHandlerMappings();
            initDispatcher();
            initViewResolvers();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void readConfigFile(FilterConfig config) {
        String res;
        if (StringUtils.isNotEmpty(config.getInitParameter(config_file))) {
            res = config.getInitParameter(config_file);
        } else
            res = "/WEB-INF/" + config.getFilterName() + "-config.json";

        InputStream stream = WebResources.getResourceAsStream(res);
        if (null != stream) {
            try {
                String text = StringUtils.readAsString(stream);
                jsonConfig = JSON.parseObject(text);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initHandlerMappings() {
        handlerMapping = new AnnotationHandlerMapping();
    }

    private void initDispatcher() {
        dispatcher = new DefaultDispatcher();
    }

    private void initViewResolvers() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        JSONArray resolver = jsonConfig.getJSONArray("viewResolver");
        JSONObject r = null;
        if (null != resolver) {
            r = resolver.getJSONObject(0);
        }

        Object o = Class.forName(r.getString("class")).newInstance();
        viewResolver = (ViewResolver) o;
        viewResolver.setViewPath(r.getJSONObject("init-params").getString("view-path"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        for (RequestEscape escape : escapes) {
            if (escape.escape(uri)) {
                chain.doFilter(request, response);
                return;
            }
        }

        FrameworkRequest frameworkRequest
                = FrameworkRequest.wrap((HttpServletRequest) request, (HttpServletResponse) response, context);

        // todo 修改了初始化的策略，但是也引入了另外一个问题，那就是在多线程下面，dispatcher会如何表现呢？
        dispatcher.service(frameworkRequest, handlerMapping, viewResolver);
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
