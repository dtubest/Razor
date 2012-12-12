package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 12-11-24
 */
public class ViewResource implements Resource {
    Resource resource;

    public ViewResource(View view) {
        resource = view.toResource();
    }

    @Override
    public String getType() {
        return resource.getType();
    }

    @Override
    public String getContentType() {
        return resource.getContentType();
    }

    @Override
    public long getLastModified() {
        return resource.getLastModified();
    }

    @Override
    public byte[] toBytes() {
        return resource.toBytes();
    }
}
