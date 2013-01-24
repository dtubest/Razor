package com.me.web.servlet.result;

import com.me.util.StringUtils;
import com.me.web.servlet.WebResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public class Todo extends Plain {
    private static final Logger log = LoggerFactory.getLogger(Todo.class);

    private static final String template;

    static {
        String str = "${message}";
        URL url = WebResources.getResourceAsURL("html/todo.html");
        if (null != url) {
            try {
                str = StringUtils.readAsString(new FileInputStream(url.getFile()));
            } catch (IOException e) {
                log.info("cannot read template file html/todo.html");
            }
        }
        template = str;
    }

    public Todo() {
        this("not finished yet!");
    }

    public Todo(String content) {
        status(501);
        type(Type.DATA);
        content(template.replace("${message}", content));
    }
}
