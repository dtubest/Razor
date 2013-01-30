package com.me.web.servlet;

import java.util.regex.Pattern;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public final class Http {
    public static final char uri_separator_char = '/';
    public static final String uri_separator = "/";
    public static final Pattern pathPattern = Pattern.compile("^/?[^?%+\\\\/#&]+(/[^?%+\\\\/#&]+)*/?$");

    private Http() {
    }
}
