package com.me.util;

import com.me.web.servlet.WebResources;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class ClassUtils {
    private static final String childPac = "[_$a-zA-Z][_$0-9a-zA-Z]*";

    private static final Pattern pacNamePattern = Pattern.compile("^" + childPac + "(\\." + childPac + ")*$");

    /**
     * 根据类名装载类
     *
     * @param name 要装载的类的完整名字
     * @return 目标类的Class对象
     */
    public static Class loadClass(String name) {
        validatePacName(name);

        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("cannot find class" + name, e);
        }
    }

    /**
     * 装载一个包下面的所有类
     *
     * @param pacName 要装载的包
     */
    public static Class[] loadClassesByPackage(String pacName) {
        validatePacName(pacName);
        URL url = WebResources.getResourceAsURL(pacName.replace(".", "/"));
        if (null == url) return new Class[0];

        File pacFile = new File(url.getPath());
        if (pacFile.isFile())
            throw new RuntimeException(pacName + " is not a valid package");

        File[] files = pacFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".class");
            }
        });

        List<Class> result = new ArrayList<Class>();

        for (File file : files) {
            String name = file.getName();
            String className = pacName + "." + name.substring(0, name.indexOf("."));
            try {
                result.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("cannot load class " + className, e);
            }
        }

        return result.toArray(new Class[result.size()]);
    }

    /**
     * 获取父包的直接子包，例如：<br>对于com.me，可以获取com.me.sub，但是不可以获取com.me.sub.sub1
     *
     * @param pacName 父包
     * @return 包含所有直接子包的数组
     */
    public static String[] getSubPackage(String pacName) {
        validatePacName(pacName);

        URL url = WebResources.getResourceAsURL(pacName.replace(".", "/"));
        if (null == url) return new String[0];

        File pacFile = new File(url.getPath());
        if (pacFile.isFile())
            throw new RuntimeException(pacName + " is not a valid package");

        File[] files = pacFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        List<String> result = new ArrayList<String>();
        for (File file : files) {
            result.add(pacName + "." + file.getName());
        }

        return result.toArray(new String[result.size()]);
    }

    public static void validatePacName(String name) {
        if (!pacNamePattern.matcher(name).matches())
            throw new RuntimeException(name + " is not a valid class name");
    }

    /////////////////////////////////////////
}
