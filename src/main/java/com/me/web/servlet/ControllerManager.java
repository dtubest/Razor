package com.me.web.servlet;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: t.ding
 * Date: 12-12-12
 */
public class ControllerManager {
    static List<String> controllers = new ArrayList<String>();
    static Map<String, String> mappings = new HashMap<String, String>();

    private static String basePackage;

    public static void registerByPackage(String basePackage) {
        controllers.clear();
        mappings.clear();

        ControllerManager.basePackage = basePackage;

        URL res;
        File file;
        if (null == (res = WebResources.getResource(basePackage.replace(".", "/")))
                || !(file = new File(res.getPath())).isDirectory())
            throw new ControllerPackageNotFoundException();

        registerByPath0(file);
    }

    private static void registerClassFile0(String name) {
        if (name.endsWith(".class")) {
            String fullName = basePackage + "." + name.substring(0, name.indexOf("."));
            controllers.add(fullName);

            try {
                Class<?> clazz = Class.forName(fullName);
                RequestMapping clazzAnnotation = clazz.getAnnotation(RequestMapping.class);
                String nameSpace = getNameSpace(clazzAnnotation);

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    if (null == methodAnnotation) continue;

                    String serviceName = getServiceName(methodAnnotation);
                    mappings.put(nameSpace + serviceName, fullName);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getServiceName(RequestMapping methodAnnotation) {
        if (null == methodAnnotation) return null;
        String value = methodAnnotation.value();
        if (null == value) value = "/";
        else if (!value.startsWith("/")) value = "/" + value;
        return value;
    }

    private static String getNameSpace(RequestMapping clazzAnnotation) {
        String value = clazzAnnotation.value();
        if (null == value) value = "";
        else if (!value.startsWith("/")) value = "/" + value;
        return value;
    }

    private static void registerByPath0(File file) {
        File[] files = file.listFiles();
        if (null == files) return;
        for (File aFile : files) {
            if (aFile.isFile()) {
                registerClassFile0(aFile.getName());
                continue;
            }
            registerByPath0(aFile);
        }
    }

    public static List<String> getControllers() {
        return controllers;
    }

    public static HandlerMethod getService(final FrameworkRequest request) {
        String clazzName = mappings.get(request.getRequestUri());
        if (null == clazzName) return null;
        Class<?> clazz = null;
        Method mtd = null;
        try {
            clazz = Class.forName(clazzName);
            if (null == clazz) return null;

            String nameSpace = getNameSpace(clazz.getAnnotation(RequestMapping.class));
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String serviceName = getServiceName(method.getAnnotation(RequestMapping.class));
                if(null == serviceName) continue;
                if (request.getRequestUri().equals(nameSpace + serviceName)) {
                    mtd = method;
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (null == mtd) return null;

        return new HandlerMethod(clazz, mtd);
    }
}
