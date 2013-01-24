package com.me.web.servlet.result;

/**
 * User: t.ding
 * Date: 13-1-23
 */
public class Redirect extends Plain {
    public Redirect(String content) {
        super(302, content, Type.REDIRECT);
    }
}
