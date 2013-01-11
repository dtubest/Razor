package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 13-1-11
 */
public class Param {
    private int index;
    private String defaultValue;

    public Param() {}

    public Param(int i) {
        index = i;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }


    public int getIndex() {
        return index;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
