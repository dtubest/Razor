package com.me.web.servlet.result;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: t.ding
 * Date: 13-1-23
 */
public class View extends Plain {
    private String mime;
    private Map<String, Object> attributes;

    public View(String content) {
        super(200, content, Type.VIEW);
        this.mime = "text/html";
        attributes = new HashMap<String, Object>();
    }

    public Object attr(String name) {
        return attributes.get(name);
    }

    public Result attr(String name, Object value) {
        attributes.put(name, value);
        return this;
    }

    public Set<String> attrKeySet() {
        return attributes.keySet();
    }

    public String mime() {
        return mime;
    }

    public Result mime(String mime) {
        this.mime = mime;
        return this;
    }
}
