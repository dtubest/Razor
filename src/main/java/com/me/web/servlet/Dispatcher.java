package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-12-10
 */
public interface Dispatcher {

    // todo 执行策略：拿到了一个handler要做什么事情？要怎么做？

    public void service(FrameworkRequest request, HandlerMapping mapping, ViewResolver resolver);
}
