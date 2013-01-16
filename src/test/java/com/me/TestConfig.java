package com.me;

import com.me.web.servlet.Config;
import com.me.web.servlet.ControllerPackage;

/**
 * User: t.ding
 * Date: 13-1-14
 */
public class TestConfig extends Config {
    @Override
    public void setControllerPackage(ControllerPackage pac) {
        pac.addPackage("com.me");
    }
}
