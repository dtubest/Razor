package com.me.web.servlet;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import test.controller.FirstController;

import static org.junit.Assert.assertThat;
/**
 * User: t.ding
 * Date: 13-1-15
 */
public class MappingTest {
    @Test
    public void test() throws NoSuchMethodException {
        Class<FirstController> control = FirstController.class;
        Mapping mapping  = new Mapping(control, control.getMethod("logic3", String.class));

        assertThat(mapping, CoreMatchers.notNullValue());
    }
}
