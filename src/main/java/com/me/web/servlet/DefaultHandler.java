package com.me.web.servlet;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * User: t.ding
 * Date: 12-12-27
 */
public class DefaultHandler implements Handler {

    private FrameworkRequest request;

    public DefaultHandler(FrameworkRequest request) {
        this.request = request;
    }

    @Override
    public Object handle() {

        try {
            request.getChain().doFilter(request.getRequest(), request.getResponse());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return null;
    }
}
