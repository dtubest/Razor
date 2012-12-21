package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-11-23
 */
public interface HandlerMapping {

    // todo 映射策略：根据请求的具体情况找到处理器，例如uri、header信息、http请求方法

    Handler getHandler(FrameworkRequest request);

    Handler getHandler(FrameworkRequest request, String uri);
}
