package com.me.web.servlet;

import com.me.web.servlet.binding.IntegerBinder;
import com.me.web.servlet.binding.StringBinder;
import javassist.*;
import javassist.bytecode.LocalVariableAttribute;

import java.lang.reflect.InvocationTargetException;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public class AnnotationMethodHandlerAdapter implements Handler {
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

    private Object[] extractParams() throws NotFoundException {
        Object[] params;

        ClassPool pool = ClassPool.getDefault();
        // todo 这里可能有一个潜在的bug，因为以后达成jar后，this.getClass()和用户的controller的path应该会不同，目前简单起见，先写成这个样子
        pool.insertClassPath(new ClassClassPath(this.getClass()));

        CtClass cc = pool.get(handler.getClazz().getName());
        CtMethod cm = cc.getDeclaredMethod(handler.getMethod().getName());
        CtClass[] parameterTypes = cm.getParameterTypes();

        if (0 == parameterTypes.length) return null;

        params = new Object[parameterTypes.length];

        LocalVariableAttribute attr = (LocalVariableAttribute)
                cm.getMethodInfo().getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < params.length; i++) {
            String name = attr.variableName(i + pos);
            String typeName = parameterTypes[i].getName();

            Binder binder = getBinder(typeName);
            Object param = binder.get(name, request);
            params[i] = param;
        }

        return params;
    }

    private Binder getBinder(String typeName) {
        if (typeName.equals("java.lang.String")) {
            return new StringBinder(handler);
        } else if (typeName.equals("java.lang.Integer")) {
            return new IntegerBinder(handler);
        }

        return null;
    }
}
