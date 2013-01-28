package com.me.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: t.ding
 * Date: 13-1-28
 */
class Request {
    private static final ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>();
    private static final ThreadLocal<HttpServletResponse> responses = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest request() {
        return requests.get();
    }

    public static void request(HttpServletRequest request) {
        requests.set(request);
    }

    public static HttpServletResponse response() {
        return responses.get();
    }

    public static void response(HttpServletResponse response) {
        responses.set(response);
    }

    public static void clear() {
        requests.remove();
        responses.remove();
    }
}
