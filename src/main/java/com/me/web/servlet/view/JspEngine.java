package com.me.web.servlet.view;

import com.me.web.servlet.DispatcherFilter;
import com.me.web.servlet.WebContext;
import com.me.web.servlet.result.Result;
import com.me.web.servlet.result.View;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: t.ding
 * Date: 13-1-22
 */
public class JspEngine implements ViewEngine {
    private String path;
    private String suffix;

    @Override
    public ViewEngine path(String path) {
        this.path = path;
        return this;
    }

    @Override
    public ViewEngine suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    @Override
    public String defaultSuffix() {
        return "jsp";
    }

    @Override
    public void render(View view, HttpServletRequest request, HttpServletResponse response) {
        if (view.type() != Result.Type.VIEW)
            throw new RuntimeException("cannot render this result");

        ServletContext servletContext
                = ((WebContext) request.getAttribute(DispatcherFilter.WEB_CONTEXT_ATTRIBUTE)).getServletContext();

        bindAttrToRequest(view, request);

        try {
            servletContext.getRequestDispatcher(path + view.content() + "." + suffix).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bindAttrToRequest(View view, HttpServletRequest request) {
        for (String key : view.attrKeySet()) {
            request.setAttribute(key, view.attr(key));
        }
    }
}
