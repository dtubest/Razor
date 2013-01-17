package com.me.web.servlet;

import com.me.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: t.ding
 * Date: 12-11-23
 */
public class DispatcherFilter implements Filter {
    public static final String WEB_CONTEXT_ATTRIBUTE = "com.me.web.servlet.DispatcherFilter.web_mvc_context_attribute";

    private static final String INIT_PARAM_NAME_USER_CONFIG_CLASS = "config-class";
    private static final Logger log = LoggerFactory.getLogger(DispatcherFilter.class);

    private String name;
    private Config mvcConfigs;
    private ControllerManager controllerManager;
    // todo 这里有必要持有一个controllerManager的应用么？
    private WebContext webContext;
    private Dispatcher dispatcher;

    @Override
    public void init(FilterConfig config) {
        name = config.getFilterName();

        String custom = config.getInitParameter(INIT_PARAM_NAME_USER_CONFIG_CLASS);
        if (null == custom) {
            mvcConfigs = new Config();
            log.info("no user config found, use default options");
        } else {
            try {
                mvcConfigs = (Config) ClassUtils.loadClass(custom).newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("user config class " + custom + " must has a default constructor", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("cannot find class " + custom, e);
            }
            log.info("user config was found, use {} to config", custom);
        }

        mvcConfigs.executeConfigs();
        controllerManager = new ControllerManager();
        webContext = new WebContext(config.getServletContext(), controllerManager);
        WebResources.init(webContext);

        String[] packages = mvcConfigs.getControllerPackages();
        for (String pac : packages) {
            controllerManager.regControllersByPackage(pac);
        }
        log.info("controller packages {}", packages);

        dispatcher = new Dispatcher();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setAttribute(WEB_CONTEXT_ATTRIBUTE, webContext);

        Mapping[] mappings = controllerManager.matches(((HttpServletRequest) request).getRequestURI());

        if (mappings.length != 1) {
            chain.doFilter(request, response);
        } else {
            Result result = dispatcher.service(mappings[0], (HttpServletRequest) request);

            ((HttpServletResponse) response).setStatus(200);
            // todo 解析result
        }
    }

    @Override
    public void destroy() {
    }
}
