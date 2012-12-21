package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public interface ViewResolver {

    // todo 视图解析策略：到底哪些东西应该放在视图解析器中呢？


    public void render(FrameworkRequest request, String view);
}
