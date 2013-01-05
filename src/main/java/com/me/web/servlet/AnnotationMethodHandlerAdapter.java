package com.me.web.servlet;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
            if (typeName.equals("java.lang.String")) {
                params[i] = request.getRequest().getParameter(name);
            } else if(typeName.equals("java.lang.Integer")) {
                params[i] = Integer.valueOf(request.getRequest().getParameter(name));
            }
        }

        return params;
    }


}
