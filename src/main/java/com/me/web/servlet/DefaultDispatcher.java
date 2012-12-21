package com.me.web.servlet;

import com.me.web.servlet.*;

/**
 * User: t.ding
 * Date: 12-12-12
 */
public class DefaultDispatcher implements Dispatcher {
    @Override
    public void service(FrameworkRequest request, HandlerMapping mapping) {
        // todo 执行，得到结果
        Handler handler = mapping.getHandler(request);
        Object result = handler.handle();

        while (Redirect.class.isAssignableFrom(result.getClass())) {
            handler = mapping.getHandler(request, ((Redirect)result).getRedirectUri());
            result = handler.handle();
        }

        // todo 处理结果
        if (void.class.isAssignableFrom(result.getClass())) {

        } else if (View.class.isAssignableFrom(result.getClass())) {

        }
    }


}
