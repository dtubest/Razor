package com.me.web.servlet.handling;

import com.me.web.servlet.FrameworkRequest;
import com.me.web.servlet.Mapping;
import javassist.NotFoundException;

import java.lang.reflect.InvocationTargetException;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public class AnnotationMethodHandlerAdapter extends ParamExtractableHandlerAdapter {
    private Mapping mapping;
    private FrameworkRequest request;

    public AnnotationMethodHandlerAdapter(Mapping mapping, FrameworkRequest request) {
        this.mapping = mapping;
        this.request = request;
    }

    @Override
    public Object handle() {
        Object invoke = null;
        try {
            Object[] params = extractParams();
            if (null == params)
                invoke = mapping.method.invoke(mapping.clazz.newInstance());
            else
                invoke = mapping.method.invoke(mapping.clazz.newInstance(), params);

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
    protected Mapping getMapping() {
        return mapping;
    }

    @Override
    protected FrameworkRequest getRequest() {
        return request;
    }
}
