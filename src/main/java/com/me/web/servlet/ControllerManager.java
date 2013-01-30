package com.me.web.servlet;

import com.me.util.ClassUtils;
import com.me.web.servlet.annotation.Exclude;
import com.me.web.servlet.result.Result;

import java.lang.reflect.Method;
import java.util.*;

/**
 * User: t.ding
 * Date: 12-12-12
 */
class ControllerManager {
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

    public Mapping[] matches(final String uri) {
        List<Mapping> result = new ArrayList<Mapping>();

        for (Mapping mapping : mappings) {
            if (mapping.match(uri))
                result.add(mapping);
        }

        return result.toArray(new Mapping[result.size()]);
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
                && superClazz != Action.class && null != superClazz)
            Collections.addAll(list, superClazz.getDeclaredMethods());

        for (Method method : list) {
            if (null != method.getAnnotation(Exclude.class)
                    || !Result.class.isAssignableFrom(method.getReturnType()))
                continue;

            mappings.add(new Mapping(clazz, method));
        }
    }
}
