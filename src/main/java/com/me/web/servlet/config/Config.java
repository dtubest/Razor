package com.me.web.servlet.config;

import com.me.web.servlet.view.JspTemplate;

/**
 * User: t.ding
 * Date: 13-1-14
 */
public class Config {
    private ControllerPackage pac = new ControllerPackage();
    private Template view = new Template();

    // 用户通过重写这些方法来进行自定义mvc行为

    protected void setControllerPackage(ControllerPackage config) {
        config.addPackage("app.controller");
    }

    protected void setViewTemplate(Template config) {
    }

    ///////////////////////////////////////////

    private void setViewTemplate0(Template config) {
        config.setPath("/");
        config.setView(JspTemplate.class);

        /**
         * 一个用户用来自定义的hook
         */
        setViewTemplate(config);
    }

    public final void executeConfigs() {
        // todo 1. 包的设置
        setControllerPackage(pac);
        // todo 2. 模板类型的设置
        setViewTemplate0(view);
        // todo 3. ？
        // todo 4. ？
        // todo 5. ？
    }

    ///////////////////////////////////////////

    public String[] getControllerPackages() {
        return pac.getPackages();
    }

    public Template getTemplate() {
        return view;
    }
}
