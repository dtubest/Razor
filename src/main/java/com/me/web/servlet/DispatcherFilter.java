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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * User: t.ding
 * Date: 12-11-23
 */
public class DispatcherFilter implements Filter {
    public static final String WEB_MVC_CONTEXT_ATTRIBUTE = "com.me.web.servlet.DispatcherFilter.web_mvc_context_attribute";

    private static final String USER_CONFIG_FILE_ATTR_NAME = "config-file";
    private static final Logger logger = LoggerFactory.getLogger(DispatcherFilter.class);

    private HandlerMapping handlerMapping;
    private Dispatcher dispatcher;
    private ViewResolver viewResolver;

    private RequestEscape[] escapes;

    private String basePackage;

    private WebContext webContext;

    private Map<String, String> params;

    @Override
    public void init(FilterConfig config) {
        /**
         * 以下各个初始化步骤不能颠倒
         */
        readFrameworkDispatcherInitParams(config);
        initFrameworkWebContext(config);
        initFrameworkStrategies(config);
        registerControllers(config);
    }

    private static final String DISPATCHER_FILTER_NAME = "dispatcher_filter_name";

    private void readFrameworkDispatcherInitParams(FilterConfig config) {
        params = new HashMap<String, String>();

        Enumeration names = config.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            params.put(name, config.getInitParameter(name));
        }

        params.put(DISPATCHER_FILTER_NAME, config.getFilterName());
    }

    private void initFrameworkWebContext(FilterConfig config) {
        webContext = new WebContext(config.getServletContext());
        WebResources.init(webContext);
    }

    private void registerControllers(FilterConfig config) {
        basePackage = config.getInitParameter("controller-package");
        if (StringUtils.isEmpty(basePackage)) {
            throw new FrameworkInitException();
        }
        ControllerManager.registerByPackage(basePackage);
    }

    private JSONObject jsonConfig;

    private void initFrameworkStrategies(FilterConfig config) {
        readConfigFile();

        try {
            initRequestEscapes();
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

    private void initRequestEscapes() {
        escapes = new RequestEscape[]{};
    }

    private void readConfigFile() {
        String file;
        if (StringUtils.isNotEmpty(params.get(USER_CONFIG_FILE_ATTR_NAME)))
            file = params.get(USER_CONFIG_FILE_ATTR_NAME);
        else
            file = "/WEB-INF/" + params.get(DISPATCHER_FILTER_NAME) + "-config.json";

        InputStream stream = WebResources.getResourceAsStream(file);

        if (null != stream)
            try {
                String text = StringUtils.readAsString(stream);
                jsonConfig = JSON.parseObject(text);

            } catch (IOException e) {
                e.printStackTrace();
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
        request.setAttribute(WEB_MVC_CONTEXT_ATTRIBUTE, webContext);

        String uri = ((HttpServletRequest) request).getRequestURI();
        for (RequestEscape escape : escapes) {
            if (escape.escape(uri)) {
                chain.doFilter(request, response);
                return;
            }
        }

        FrameworkRequest frameworkRequest
                = FrameworkRequest.wrap((HttpServletRequest) request, (HttpServletResponse) response, webContext);

        // todo 修改了初始化的策略，但是也引入了另外一个问题，那就是在多线程下面，dispatcher会如何表现呢？
        dispatcher.service(frameworkRequest, handlerMapping, viewResolver);
    }

    @Override
    public void destroy() {
    }
}
