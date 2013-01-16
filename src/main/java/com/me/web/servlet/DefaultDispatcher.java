package com.me.web.servlet;

import com.me.json.JsonResult;
import com.me.web.servlet.handling.DefaultUrlHandlerAdapter;

import java.io.IOException;

/**
 * User: t.ding
 * Date: 12-12-12
 */
public class DefaultDispatcher implements Dispatcher {
    @Override
    public void service(FrameworkRequest request, HandlerMapping mapping, ViewResolver resolver) {
        // todo 执行，得到结果
        Handler handler = mapping.getHandler(request);

        if (null == handler) {
            new DefaultUrlHandlerAdapter(request).handle();
            return;
        }
        Object result = handler.handle();

        while (null != result && Forward.class.isAssignableFrom(result.getClass())) {
            handler = mapping.getHandler(request, ((Forward) result).getRedirectUri());
            result = handler.handle();
        }

        // todo 处理结果
        if (null == result) return;

        if (String.class.isAssignableFrom(result.getClass())) {
            resolver.render(request, (String) result);

        } else if (JsonResult.class.isAssignableFrom(result.getClass())) {
            try {
                request.getResponse().getWriter().write(((JsonResult) result).toJson());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (Forward.class.isAssignableFrom(result.getClass())) {

        } else if (Redirect.class.isAssignableFrom(result.getClass())) {

        }
    }
}
