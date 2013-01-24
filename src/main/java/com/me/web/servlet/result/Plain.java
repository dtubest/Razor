package com.me.web.servlet.result;

/**
 * User: t.ding
 * Date: 13-1-23
 */
public class Plain implements Result {
    private int status;
    private String content;
    private Type type;

    public Plain() {
    }

    public Plain(int status, String content, Type type) {
        this.status = status;
        this.content = content;
        this.type = type;
    }

    @Override
    public int status() {
        return status;
    }

    @Override
    public Result status(int status) {
        if (type() != Type.REDIRECT)
            this.status = status;
        return this;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public Result type(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public String content() {
        return content;
    }

    @Override
    public Result content(String content) {
        this.content = content;
        return this;
    }
}
