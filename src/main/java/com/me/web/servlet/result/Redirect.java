package com.me.web.servlet.result;

/**
 * User: t.ding
 * Date: 13-1-23
 */
public class Redirect implements Result {
    private String content;

    public Redirect(String content) {
        this.content = content;
    }

    @Override
    public int status() {
        return 302;
    }

    @Override
    public Type type() {
        return Type.REDIRECT;
    }

    @Override
    public String content() {
        return content;
    }
}
