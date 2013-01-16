package com.me.web.servlet;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.*;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.*;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class DispatcherFilterTest {
    private FilterConfig config;

    @Before
    public void before() {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("configClass", "com.me.TestConfig");

        config = new FilterConfig() {
            @Override
            public String getFilterName() {
                return "just";
            }

            @Override
            public ServletContext getServletContext() {
                return new ServletContext() {
                    @Override
                    public String getContextPath() {
                        return null;
                    }

                    @Override
                    public ServletContext getContext(String uripath) {
                        return null;
                    }

                    @Override
                    public int getMajorVersion() {
                        return 0;
                    }

                    @Override
                    public int getMinorVersion() {
                        return 0;
                    }

                    @Override
                    public String getMimeType(String file) {
                        return null;
                    }

                    @Override
                    public Set getResourcePaths(String path) {
                        return null;
                    }

                    @Override
                    public URL getResource(String path) throws MalformedURLException {
                        return null;
                    }

                    @Override
                    public InputStream getResourceAsStream(String path) {
                        return null;
                    }

                    @Override
                    public RequestDispatcher getRequestDispatcher(String path) {
                        return null;
                    }

                    @Override
                    public RequestDispatcher getNamedDispatcher(String name) {
                        return null;
                    }

                    @Override
                    public Servlet getServlet(String name) throws ServletException {
                        return null;
                    }

                    @Override
                    public Enumeration getServlets() {
                        return null;
                    }

                    @Override
                    public Enumeration getServletNames() {
                        return null;
                    }

                    @Override
                    public void log(String msg) {
                    }

                    @Override
                    public void log(Exception exception, String msg) {
                    }

                    @Override
                    public void log(String message, Throwable throwable) {
                    }

                    @Override
                    public String getRealPath(String path) {
                        return null;
                    }

                    @Override
                    public String getServerInfo() {
                        return null;
                    }

                    @Override
                    public String getInitParameter(String name) {
                        return null;
                    }

                    @Override
                    public Enumeration getInitParameterNames() {
                        return null;
                    }

                    @Override
                    public Object getAttribute(String name) {
                        return null;
                    }

                    @Override
                    public Enumeration getAttributeNames() {
                        return null;
                    }

                    @Override
                    public void setAttribute(String name, Object object) {
                    }

                    @Override
                    public void removeAttribute(String name) {
                    }

                    @Override
                    public String getServletContextName() {
                        return null;
                    }
                };
            }

            @Override
            public String getInitParameter(String name) {
                return map.get(name);
            }

            @Override
            public Enumeration getInitParameterNames() {
                return new Enumeration() {
                    Iterator<String> iterator = map.keySet().iterator();

                    @Override
                    public boolean hasMoreElements() {
                        return iterator.hasNext();
                    }

                    @Override
                    public Object nextElement() {
                        return iterator.next();
                    }
                };
            }
        };
    }

    @Test
    public void testConfig() {
        assertNotNull(config);
        assertNull(config.getInitParameter("configClass-xx"));
        assertNotNull(config.getInitParameter("configClass"));
        assertNotNull(config.getServletContext());
        assertNotNull(config.getInitParameterNames());
        assertEquals("msg", "just", config.getFilterName());
    }

    @Test
    public void me() {
        DispatcherFilter filter = new DispatcherFilter();

        Package pac = Package.getPackage("com.me");
        System.out.println(pac.getAnnotations());
    }

}
