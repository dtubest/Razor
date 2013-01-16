package com.me.web.servlet;

import org.junit.Test;
import test.controller.FirstController;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class MappingTest {
    @Test
    public void test() {
        Class<FirstController> control = FirstController.class;
        Mapping mapping = null;
        try {
            mapping = new Mapping(control, control.getMethod("logic3", String.class));
            System.out.println("uri----------------------------------------" + mapping.uri);
        } catch (NoSuchMethodException e) {

        }

    }
}
