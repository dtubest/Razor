package com.me.web.servlet.handler;

import com.me.web.servlet.FrameworkRequest;
import com.me.web.servlet.Handler;
import com.me.web.servlet.HandlerMethod;
import com.me.web.servlet.binding.Binder;
import javassist.*;
import javassist.bytecode.LocalVariableAttribute;

/**
 * User: t.ding
 * Date: 13-1-11
 */
public abstract class ParamExtractableHandlerAdapter implements Handler {
    protected Object[] extractParams() throws NotFoundException {
        Object[] params;

        ClassPool pool = ClassPool.getDefault();
        // todo 这里可能有一个潜在的bug，因为以后达成jar后，this.getClass()和用户的controller的path应该会不同，目前简单起见，先写成这个样子
        pool.insertClassPath(new ClassClassPath(this.getClass()));

        HandlerMethod handler = getHandler();

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

            Binder binder = Binder.createBinder(typeName, handler);
            Object param = binder.get(name, getRequest());
            params[i] = param;
        }

        return params;
    }

    protected abstract HandlerMethod getHandler();

    protected abstract FrameworkRequest getRequest();
}
