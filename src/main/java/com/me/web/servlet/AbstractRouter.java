package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-11-24
 */
public abstract class AbstractRouter implements Router {
    @Override
    public Resource route(String uri) {
        RouteStrategy strategy = getStrategy();
        // todo 根据uri找到对应的处理器
        Handler handler = strategy.acceptForHandler(uri);

        if (null == handler) return null;

        View view = handler.process();
        if (null == view) return null;

        return new ViewResource(view);
    }

    protected abstract RouteStrategy getStrategy();

}
