package com.me.web.servlet.handling;

import com.me.web.servlet.FrameworkRequest;
import com.me.web.servlet.Handler;
import com.me.web.servlet.Mapping;
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

        Mapping mapping = getMapping();
        pool.insertClassPath(new ClassClassPath(mapping.clazz));

        CtMethod cm = pool.get(mapping.clazz.getName()).getDeclaredMethod(mapping.method.getName());
        CtClass[] parameterTypes = cm.getParameterTypes();

        if (0 == parameterTypes.length) return new Object[0];

        params = new Object[parameterTypes.length];

        LocalVariableAttribute attr = (LocalVariableAttribute)
                cm.getMethodInfo().getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < params.length; i++) {
            String name = attr.variableName(i + pos);
            String typeName = parameterTypes[i].getName();

            Binder binder = Binder.binderOf(typeName);
            Object param = binder.get(name, getRequest(), mapping);
            params[i] = param;
        }

        return params;
    }

    protected abstract Mapping getMapping();

    protected abstract FrameworkRequest getRequest();
}
