package com.me.web.servlet.handling;

import com.me.web.servlet.FrameworkRequest;
import com.me.web.servlet.Handler;
import com.me.web.servlet.Result;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * User: t.ding
 * Date: 12-12-27
 */
public class DefaultUrlHandlerAdapter implements Handler {
    private FrameworkRequest request;

    public DefaultUrlHandlerAdapter(FrameworkRequest request) {
        this.request = request;
    }

    @Override
    public Result handle() {
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
