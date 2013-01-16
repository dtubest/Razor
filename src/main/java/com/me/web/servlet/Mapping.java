package com.me.web.servlet;

import com.me.web.servlet.annotation.Path;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class Mapping {
    public final Class<?> clazz;
    public final Method method;
    public final String uri;

    private final Pattern pattern;

    public Mapping(Class<?> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
        this.uri = getPath(clazz.getAnnotation(Path.class), clazz.getSimpleName())
                + getPath(method.getAnnotation(Path.class), method.getName());

        pattern = getPattern();
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

    private Pattern getPattern() {
        String innerUri = "^" + uri + "$";
        return Pattern.compile(innerUri.replaceAll("\\{[a-zA-Z]+\\}", "[\\\\S]+"));
    }

    private String getPath(Path path, String dName) {
        if (null == path)
            return Http.uri_separator + dName;

        if (path.value() == null || !Http.pathPattern.matcher(path.value()).matches())
            throw new RuntimeException("Path value is not valid [" + path.value() + "]");

        String result = path.value();
        char[] chars = result.toCharArray();

        int length;
        if (result.endsWith(Http.uri_separator))
            length = chars.length - 1;
        else
            length = chars.length;

        StringBuilder builder = new StringBuilder();
        if (chars[0] != Http.uri_separator_char)
            builder.append(Http.uri_separator_char);

        builder.append(chars, 0, length);
        return builder.toString();
    }

}
