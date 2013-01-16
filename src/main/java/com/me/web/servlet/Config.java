package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 13-1-14
 */
public class Config {
    private ControllerPackage pac = new ControllerPackage();

    public void setControllerPackage(ControllerPackage pac) {
        pac.addPackage("app.controller");
    }

    public final void executeConfigs() {
        // todo 1. 包的设置
        setControllerPackage(pac);
        // todo 2. 模板类型的设置
        // todo 3. ？
        // todo 4. ？
        // todo 5. ？
    }

    public String[] getControllerPackages() {
        return pac.getPackages();
    }
}
