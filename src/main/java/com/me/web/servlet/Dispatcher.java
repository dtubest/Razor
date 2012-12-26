package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-12-10
 */
public interface Dispatcher {
    public static final String WEB_MVC_CONTEXT_ATTRIBUTE = DispatcherFilter.WEB_MVC_CONTEXT_ATTRIBUTE;

    // todo 执行策略：拿到了一个handler要做什么事情？要怎么做？

    public void service(FrameworkRequest request, HandlerMapping mapping, ViewResolver resolver);
}
