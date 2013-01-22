package com.me.web.servlet.result;

import com.me.web.servlet.Result;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public class Todo implements Result {
    @Override
    public int status() {
        return 501;
    }

    @Override
    public String contentType() {
        return null;
    }

    @Override
    public byte[] wrappedContent() {
        return new byte[0];
    }
}
