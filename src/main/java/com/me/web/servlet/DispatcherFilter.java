package com.me.web.servlet;

import com.me.web.servlet.router.DefaultRouter;
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Context.setServletContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        // todo 过滤掉静态资源的请求，直接交给容器处理
        if (requestURI.toLowerCase().endsWith("jpg")) {
            chain.doFilter(request, response);
            return;
        }
//        maybeSetHttpCache(httpRequest, httpResponse);

        // todo 这里其实是获取映射策略，router就是对策略的封装
        Router router = getRouter();
        Resource resource = router.route(httpRequest.getRequestURI());

        if (null != resource)
            sendResource(response, resource);
        else
            doFilter(request, response, chain);
    }

    private void ignoreURI(String requestURI, FilterChain chain) {

    }

    protected Router getRouter() {
        return new DefaultRouter();
    }

    @Override
    public void destroy() {
    }

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

    private void sendResource(ServletResponse response, Resource resource)
            throws IOException {
        response.setContentType(resource.getContentType());
        response.getOutputStream().write(resource.toBytes());
    }
}
