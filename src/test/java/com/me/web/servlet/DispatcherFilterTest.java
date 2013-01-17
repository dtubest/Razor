package com.me.web.servlet;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class DispatcherFilterTest {
    @Test
    public void testWithMock() {
        DispatcherFilter filter = new DispatcherFilter();
        Answer answer = new Answer() {
            @Override
            public Object answer(InvocationOnMock invoke) throws Throwable {
                if (invoke.getMethod().getName().equalsIgnoreCase("getFilterName"))
                    return "just";

                if (invoke.getMethod().getName().equalsIgnoreCase("getServletContext"))
                    return mock(ServletContext.class);

                return null;
            }
        };
        FilterConfig config = mock(FilterConfig.class, answer);

        filter.init(config);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        try {
            filter.doFilter(request, response, chain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
