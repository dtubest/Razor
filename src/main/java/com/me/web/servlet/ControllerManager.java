package com.me.web.servlet;

import com.me.util.ClassUtils;
import com.me.web.servlet.annotation.Exclude;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * User: t.ding
 * Date: 12-12-12
 */
public class ControllerManager {
    private Set<Mapping> mappings = new HashSet<Mapping>();

    public void regControllersByPackage(String pacName) {
        Class[] classes = ClassUtils.loadClassesByPackage(pacName);
        for (Class clazz : classes) {
            registerClassFile1(clazz);
        }

        String[] subs = ClassUtils.getSubPackage(pacName);
        for (String sub : subs) {
            regControllersByPackage(sub);
        }
    }

    // todo 这个方法需要测试
    public Mapping getService(final FrameworkRequest request) {
        for (Mapping mapping : mappings) {
            String uri = request.getRequest().getRequestURI();
            if (mapping.match(uri))
                return mapping;
        }

        return null;
    }

    public Set<Mapping> getMappings() {
        // todo 思考这两种做法的区别和好坏
//        return mappings.toArray(new Mapping[mappings.size()]);
        return mappings;
    }

    /////////////////////////////////////////////

    private void registerClassFile1(Class<?> clazz) {
        if (null != clazz.getAnnotation(Exclude.class)) return;


        ArrayList<Method> list = new ArrayList<Method>();
        Collections.addAll(list, clazz.getDeclaredMethods());

        Class<?> superClazz = clazz;
        while ((superClazz = superClazz.getSuperclass()) != Object.class
                && superClazz != Results.class && null != superClazz)
            Collections.addAll(list, superClazz.getDeclaredMethods());

        for (Method method : list) {
            if (null != method.getAnnotation(Exclude.class)
                    || !Result.class.isAssignableFrom(method.getReturnType()))
                continue;

            mappings.add(new Mapping(clazz, method));
        }
    }
}
