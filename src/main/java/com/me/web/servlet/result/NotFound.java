package com.me.web.servlet.result;

import com.me.util.StringUtils;
import com.me.web.servlet.WebResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public class NotFound extends Plain {
    private static final Logger log = LoggerFactory.getLogger(NotFound.class);

    private static final String template;

    static {
        String str = "${message}";
        URL url = WebResources.getResourceAsURL("html/not_found.html");
        if (null != url) {
            try {
                str = StringUtils.readAsString(new FileInputStream(url.getFile()));
            } catch (IOException e) {
                log.info("cannot read template file html/not_found.html");
            }
        }
        template = str;
    }

    public NotFound() {
        this("not found!");
    }

    public NotFound(String content) {
        status(404);
        type(Type.DATA);
        content(template.replace("${message}", content));
    }
}
