package com.me.web.servlet;

import com.me.web.servlet.binding.Binder;
import com.me.web.servlet.result.Result;
import javassist.*;
import javassist.bytecode.LocalVariableAttribute;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * User: t.ding
 * Date: 12-12-12
 */
public class Dispatcher {

    /**
     * 执行目标类的目标方法
     *
     * @param mapping 包含了目标类和方法的Mapping对象
     * @param request 包含了请求和响应的对象
     * @return 执行目标方法后的结果
     */
    public Result service(Mapping mapping, HttpServletRequest request) {
        Object[] params = extractParams(mapping, request);

        Result result = null;
        try {
            if (null == params)
                result = (Result) mapping.method.invoke(mapping.clazz.newInstance());
            else
                result = (Result) mapping.method.invoke(mapping.clazz.newInstance(), params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("cannot access controller class " + mapping.clazz.getName(), e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException("controller class " + mapping.clazz.getName() + " dosen't has a default controller", e);
        }
        return result;
    }

    private Object[] extractParams(Mapping mapping, HttpServletRequest request) {
        Object[] params;

        ClassPool pool = ClassPool.getDefault();

        pool.insertClassPath(new ClassClassPath(mapping.clazz));

        CtMethod cm = null;
        CtClass[] parameterTypes = new CtClass[0];
        try {
            cm = pool.get(mapping.clazz.getName()).getDeclaredMethod(mapping.method.getName());
            parameterTypes = cm.getParameterTypes();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        if (0 == parameterTypes.length) return new Object[0];

        params = new Object[parameterTypes.length];

        LocalVariableAttribute attr = (LocalVariableAttribute)
                cm.getMethodInfo().getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < params.length; i++) {
            String name = attr.variableName(i + pos);
            String typeName = parameterTypes[i].getName();

            Binder binder = Binder.valueOf(typeName);
            Object param = binder.get(name, request, mapping);
            params[i] = param;
        }

        return params;
    }
}
