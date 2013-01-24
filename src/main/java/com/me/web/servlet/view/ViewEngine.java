package com.me.web.servlet.view;

import com.me.web.servlet.result.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public interface ViewEngine {
    public ViewEngine path(String path);

    public ViewEngine suffix(String suffix);

    public String defaultSuffix();

    public void render(View result, HttpServletRequest request, HttpServletResponse response);
}
