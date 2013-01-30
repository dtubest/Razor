package com.me.web.servlet.view;

import com.me.web.servlet.result.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public interface ViewEngine {
    /**
     * 设置模板的路径
     *
     * @param path 路径String对象
     * @return ViewEngine对象，支持链式调用
     */
    public ViewEngine path(String path);

    /**
     * 设置模板文件的后缀
     *
     * @param suffix 后缀
     * @return ViewEngine对象，支持链式调用
     */
    public ViewEngine suffix(String suffix);

    /**
     * 模板文件的默认后缀
     *
     * @return 默认后缀
     */
    public String defaultSuffix();

    /**
     * 解析模板
     *
     * @param view     View对象
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     */
    public void render(View view, HttpServletRequest request, HttpServletResponse response);
}
