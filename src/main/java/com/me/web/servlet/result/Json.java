package com.me.web.servlet.result;

/**
 * User: t.ding
 * Date: 13-1-23
 */
public class Json extends MutableResult {
    public Json() {
        this(200, "", Type.DATA);
    }

    public Json(int status, String content, Type type) {
        super(status, content, type);
    }
}
