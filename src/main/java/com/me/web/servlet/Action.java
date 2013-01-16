package com.me.web.servlet;

import com.me.web.servlet.annotation.Exclude;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: t.ding
 * Date: 13-1-10
 */
public class Action extends Results {
    HttpServletRequest request = null;

    public HttpServletRequest request() {
        return null;
    }

    public Object request(String name) {
        return request.getAttribute(name);
    }

    public void request(String name, Object value) {
        request.setAttribute(name, value);
    }

    public HttpServletResponse response() {
        return null;
    }

    public Object response(String name) {
        return null;
    }

    public void response(String name, Object value) {
    }

    public String param(String name) {
        return request.getParameter(name);
    }

    @Exclude
    public Result ok() {
        return null;
    }

    @Exclude
    public Result redirect() {
        return null;
    }
}
