package com.me.web.servlet.result;

import com.me.web.servlet.Result;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public class NotFound implements Result {
    @Override
    public int status() {
        return 404;
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
