package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-11-23
 */
public interface Resource {
    String getType();

    String getContentType();

    long getLastModified();

    byte[] toBytes();
}
