package com.me.web.servlet.config;

import com.me.web.servlet.view.ViewEngine;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public class Template {
    private String path;
    private String suffix;
    private Class<? extends ViewEngine> engine;

    public String path() {
        return path;
    }

    public Template path(String path) {
        this.path = path;
        return this;
    }

    public String suffix() {
        return suffix;
    }

    public Template suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public Class<? extends ViewEngine> engine() {
        return engine;
    }

    public Template engine(Class<? extends ViewEngine> engine) {
        this.engine = engine;
        return this;
    }

    public ViewEngine createEngine() {
        try {
            ViewEngine ve = engine.newInstance();
            if (null == suffix) {
                suffix = ve.defaultSuffix();
            }

            return ve.path(path)
                    .suffix(suffix);
        } catch (InstantiationException e) {
            throw new RuntimeException("ViewEngine " + engine.getName()
                    + " did not have a default constructor", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
