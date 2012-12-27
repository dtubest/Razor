package com.me.web.servlet.mapping;

import com.me.web.servlet.*;

/**
 * User: t.ding
 * Date: 12-11-24
 */
public class AnnotationHandlerMapping implements HandlerMapping {
    @Override
    public Handler getHandler(FrameworkRequest request) {
        return getHandler(request, null);
    }

    @Override
    public Handler getHandler(FrameworkRequest request, String uri) {
        request.setTargetUri(uri);
        HandlerMethod handlerMethod = request.getFrameworkContext().getControllerManager().getService(request);
        if (null == handlerMethod) return null;

        return new AnnotationMethodHandlerAdapter(handlerMethod);
    }
}
