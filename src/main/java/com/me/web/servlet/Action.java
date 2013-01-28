package com.me.web.servlet;

import com.me.web.servlet.result.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户的Controller应该继承自这个类(虽然不是必须的)，ControllerManager会
 * 在继承层次中逐级往上扫描action，但是这个类中的所有方法都不会被扫描
 * <p/>
 * User: t.ding
 * Date: 13-1-10
 */
public class Action {
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;

    /**
     * 获取HttpServletRequest对象
     *
     * @return HttpServletRequest对象
     */
    public HttpServletRequest request() {
        if (null == request)
            request = Request.request();
        return request;
    }

    /**
     * 从request中获取attribute
     *
     * @param name attribute名
     * @return attribute
     */
    public Object request(String name) {
        return request.getAttribute(name);
    }

    /**
     * 向request中添加attribute
     *
     * @param name  attribute名
     * @param value attribute值
     */
    public void request(String name, Object value) {
        request.setAttribute(name, value);
    }

    /**
     * 获取HttpServletResponse对象
     *
     * @return HttpServletResponse对象
     */
    public HttpServletResponse response() {
        if (null == response)
            response = Request.response();
        return response;
    }

    /**
     * 从request中获取参数param
     *
     * @param name param
     * @return param值
     */
    public String param(String name) {
        return request.getParameter(name);
    }

    /**
     * 获取名为name的Cookie对象
     *
     * @param name cookie名
     * @return Cookie对象
     */
    public Cookie cookie(String name) {
        Cookie[] cookies = cookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name))
                return cookie;
        }
        return null;
    }

    /**
     * 设置cookie
     *
     * @param cookie Cookie对象
     */
    public void cookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    /**
     * 清除cookie
     *
     * @param name cookie 名
     */
    public void removeCookie(String name) {
    }

    public Cookie[] cookies() {
        return request.getCookies();
    }

    /**
     * 创建名称为name的视图
     *
     * @param name 视图名
     */
    public View view(String name) {
        return new View(name);
    }

    /**
     * 创建一个空的Json对象：{}
     *
     * @return 空Json对象
     */
    public Json json() {
        return new Json();
    }

    /**
     * 构造一个状态码为200，内容为空的DATA结果
     *
     * @return 内容为空的，成功状态
     */
    public Result ok() {
        return ok("");
    }

    /**
     * 构造一个状态码为200，内容为content的结果
     *
     * @param content 内容
     * @return 内容为content的成功状态
     */
    public Result ok(String content) {
        return new Ok(content);
    }

    /**
     * 将view状态码修改为200，并返回view
     *
     * @param view 视图
     * @return view
     */
    public View ok(View view) {
        view.status(200);
        return view;
    }

    /**
     * 将json状态码修改为200，并返回json
     *
     * @param json 视图
     * @return json
     */
    public Json ok(Json json) {
        json.status(200);
        return json;
    }

//    /**
//     * 构造一个状态码为status的结果
//     *
//     * @param status 状态码
//     * @return 结果
//     */
//    public Result status(int status) {
//        return status(status, "");
//    }
//
//    /**
//     * 构造一个状态码为status，内容为content的结果
//     *
//     * @param status 状态码
//     * @return 结果
//     */
//    public Result status(int status, String content) {
//        return new MutableResult(status, content, DATA);
//    }

    /**
     * 构造一个Redirect对象
     *
     * @param uri 重定向目标uri
     * @return Redirect对象
     */
    public Result redirect(String uri) {
        return new Redirect(uri);
    }
}
