//package com.me.web.servlet.view;
//
//import com.me.web.servlet.Context;
//import com.me.web.servlet.Resource;
//import com.me.web.servlet.View;
//
//import javax.servlet.ServletContext;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * User: t.ding
// * Date: 12-12-8
// */
//public class TextView implements View {
//    String view;
//    Map<String, Object> attributes = new HashMap<String, Object>();
//
//
//    public TextView(String s) {
//        view = s;
//    }
//
//    @Override
//    public Resource toResource() {
//
//        ServletContext context = Context.getServletContext();
//        InputStream in = context.getResourceAsStream(view);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        final StringBuilder buffer = new StringBuilder();
//
//        try {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Resource resource = new Resource() {
//            @Override
//            public String getType() {
//                return "de-type";
//            }
//
//            @Override
//            public String getContentType() {
//                return "text/html";
//            }
//
//            @Override
//            public long getLastModified() {
//                return -1;
//            }
//
//            @Override
//            public byte[] toBytes() {
//                return buffer.toString().getBytes();
//            }
//        };
//        return resource;
//    }
//
//    @Override
//    public void setAttribute(String name, Object value) {
//        attributes.put(name, value);
//    }
//}
