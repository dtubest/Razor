package com.me.web.servlet;

import com.me.web.servlet.annotation.Exclude;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.me.web.servlet.Results.*;

/**
 * User: t.ding
 * Date: 13-1-10
 */

/**
 * Action类中不定义action，只定义一系列的帮助方法,controllerManager在扫描action
 * 时，不会扫描这个类中的任何方法
 */
public class Action {
    HttpServletRequest request = null;
    HttpServletResponse response = null;

    public HttpServletRequest request() {
        return request;
    }

    public Object request(String name) {
        return request.getAttribute(name);
    }

    public void request(String name, Object value) {
        request.setAttribute(name, value);
    }

    public HttpServletResponse response() {
        return response;
    }

    public String param(String name) {
        return request.getParameter(name);
    }

    public Cookie cookie(String name) {
        Cookie[] cookies = cookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name))
                return cookie;
        }
        return null;
    }

    public void cookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    public void removeCookie(String name) {
    }

    public Cookie[] cookies() {
        return request.getCookies();
    }

    public void view(String name) {
    }

    public void view() {
    }

    @Exclude
    public Result ok() {
        return OK;
    }

    @Exclude
    public Result ok(String content) {
        return null;
    }

    @Exclude
    public Result ok(Result view) {
        return null;
    }

    public int status() {
        return -1;
    }

    @Exclude
    public Result status(int status) {
        return null;
    }

    @Exclude
    public Result redirect() {
        return null;
    }
}
