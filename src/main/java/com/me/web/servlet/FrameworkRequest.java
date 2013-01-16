package com.me.web.servlet;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: t.ding
 * Date: 12-12-20
 */
// todo 感觉这是一个设计的很蹩脚的类
public class FrameworkRequest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;
    private String targetUri;

    private WebContext context;

    public FrameworkRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        this.request = request;
        this.response = response;
        this.chain = chain;
    }

    public String getRequestUri() {
        if (null == targetUri)
            return request.getRequestURI();
        return targetUri;
    }

    public void setTargetUri(String uri) {
        targetUri = uri;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public FilterChain getChain() {
        return chain;
    }

    public WebContext getFrameworkContext() {
        return context;
    }

    public static FrameworkRequest wrap(HttpServletRequest request, HttpServletResponse response, FilterChain chain, WebContext context) {
        FrameworkRequest frameworkRequest = new FrameworkRequest(request, response, chain);
        frameworkRequest.context = context;
        return frameworkRequest;
    }
}
