package com.me.web.servlet;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * User: t.ding
 * Date: 12-12-21
 */
public class JspViewResolver implements ViewResolver {
    @Override
    public void setViewPath(String path) {
    }

    @Override
    public void render(FrameworkRequest request, String view) {
        String viewPath = "";

        try {
            request.getFrameworkContext().getServletContext()
                    .getRequestDispatcher("/WEB-INF/test.jsp")
                    .forward(request.getRequest(), request.getResponse());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
