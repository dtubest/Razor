package com.me.web.servlet;

import com.me.util.StringUtils;
import com.me.web.servlet.annotation.Exclude;
import com.me.web.servlet.annotation.Path;
import com.me.web.servlet.exception.ControllerPackageNotFoundException;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * User: t.ding
 * Date: 12-12-12
 */
public class ControllerManager {
    public static class Mapping {
        public final Class<?> clazz;
        public final Method method;
        public final String uri;

        private final Pattern pattern;

        public Mapping(Class<?> clazz, Method method, String uri) {
            this.clazz = clazz;
            this.method = method;
            this.uri = uri;
            pattern = getPattern(uri);
        }

        public boolean match(String uri) {
            return pattern.matcher(uri).matches();
        }

        public int getPathParamIndex(String name) {
            Pattern pattern = Pattern.compile("\\{" + name + "\\}");
            String[] strings = uri.split("/");

            for (int i = 1; i < strings.length; i++) {
                if (pattern.matcher(strings[i]).matches())
                    return i - 1;
            }
            return -1;
        }

        ////////////////////////////////////////////////////

        private Pattern getPattern(String uri) {
            String innerUri = "^" + uri + "$";
            return Pattern.compile(innerUri.replaceAll("\\{[a-zA-Z]+\\}", "[\\\\S]+"));
        }
    }

    private static final char url_path_separator_char = '/';
    private static final String url_path_separator = "/";

    private Set<Mapping> mappings = new HashSet<Mapping>();

    private String basePackage;

    public void registerByPackage(String basePackage) {
        mappings.clear();

        this.basePackage = basePackage;

        URL res;
        File file;
        if (null == (res = WebResources.getResourceAsURL(basePackage.replace(".", "/")))
                || !(file = new File(res.getPath())).isDirectory())
            throw new ControllerPackageNotFoundException();

        registerByPath0(file);
    }

    public HandlerMethod getService(final FrameworkRequest request) {
        for (Mapping mapping : mappings) {
            String uri = request.getRequest().getRequestURI();
            if (mapping.match(uri))
                return new HandlerMethod(mapping);
        }

        return null;
    }

    public Set<Mapping> getMappings() {
        return mappings;
    }

    /////////////////////////////////////////////

    private void registerClassFile1(String name) {
        if (name.endsWith(".class")) {
            String fullName = basePackage + "." + name.substring(0, name.indexOf("."));

            try {
                Class<?> clazz = Class.forName(fullName);
                if (null != clazz.getAnnotation(Exclude.class)) return;

                String nameSpace = getPath(clazz.getAnnotation(Path.class), clazz.getSimpleName());

                ArrayList<Method> list = new ArrayList<Method>();
                Collections.addAll(list, clazz.getDeclaredMethods());

                Class<?> superClazz = clazz;

                while ((superClazz = superClazz.getSuperclass()) != Object.class && null != superClazz) {
                    Collections.addAll(list, superClazz.getDeclaredMethods());
                }

                for (Method method : list) {
                    if (null != method.getAnnotation(Exclude.class)) continue;

                    String serviceName = getPath(method.getAnnotation(Path.class), method.getName());

                    mappings.add(new Mapping(clazz, method, nameSpace + serviceName));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String getPath(Path path, String dName) {
        if (null == path || path.value() == null || StringUtils.containsBlank(path.value()))
            return getPath0(dName);

        return getPath0(path.value());
    }

    private String getPath0(String dName) {
        StringBuilder value = new StringBuilder(url_path_separator);
        char[] chars = dName.toCharArray();
        for (char aChar : chars)
            if (url_path_separator_char != aChar)
                value.append(aChar);
            else if (value.charAt(value.length() - 1) != url_path_separator_char)
                value.append(aChar);

        if (value.charAt(value.length() - 1) == url_path_separator_char)
            value.deleteCharAt(value.length() - 1);

        return value.toString();
    }

    private void registerByPath0(File file) {
        File[] files = file.listFiles();
        if (null == files) return;
        for (File aFile : files) {
            if (aFile.isFile()) {
                registerClassFile1(aFile.getName());
                continue;
            }
            registerByPath0(aFile);
        }
    }
}
