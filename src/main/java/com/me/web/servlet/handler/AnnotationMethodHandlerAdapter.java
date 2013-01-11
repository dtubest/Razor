package com.me.web.servlet.handler;

import com.me.web.servlet.FrameworkRequest;
import com.me.web.servlet.HandlerMethod;
import javassist.NotFoundException;

import java.lang.reflect.InvocationTargetException;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public class AnnotationMethodHandlerAdapter extends ParamExtractableHandlerAdapter {
    private HandlerMethod handler;
    private FrameworkRequest request;

    public AnnotationMethodHandlerAdapter(HandlerMethod handler, FrameworkRequest request) {
        this.handler = handler;
        this.request = request;
    }

    @Override
    public Object handle() {
        Object invoke = null;
        try {
            Object[] params = extractParams();
            if (null == params)
                invoke = handler.getMethod().invoke(handler.getClazz().newInstance());
            else
                invoke = handler.getMethod().invoke(handler.getClazz().newInstance(), params);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return invoke;
    }

    @Override
    protected HandlerMethod getHandler() {
        return handler;
    }

    @Override
    protected FrameworkRequest getRequest() {
        return request;
    }
}
