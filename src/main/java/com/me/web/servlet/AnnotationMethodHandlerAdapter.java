package com.me.web.servlet;

import java.lang.reflect.InvocationTargetException;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public class AnnotationMethodHandlerAdapter implements Handler {
    private HandlerMethod handler;

    public AnnotationMethodHandlerAdapter(HandlerMethod handler) {
        this.handler = handler;
    }

    @Override
    public Object handle() {
        Object invoke = null;
        try {
            invoke = handler.getMethod().invoke(handler.getClazz().newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return invoke;
    }
}
