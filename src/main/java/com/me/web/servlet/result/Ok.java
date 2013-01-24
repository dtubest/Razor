package com.me.web.servlet.result;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public class Ok extends Plain {
    public Ok() {
        this("");
    }

    public Ok(String content) {
        super(200, content, Type.DATA);
    }
}
