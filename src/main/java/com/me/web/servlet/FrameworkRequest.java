package com.me.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: t.ding
 * Date: 12-12-20
 */
public class FrameworkRequest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String targetUri;

    private Context context;

    public FrameworkRequest(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
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

    public Context getFrameworkContext() {
        return context;
    }

    public static FrameworkRequest wrap(HttpServletRequest request, HttpServletResponse response, Context context) {
        FrameworkRequest frameworkRequest = new FrameworkRequest(request, response);
        frameworkRequest.context = context;
        return frameworkRequest;
    }
}
