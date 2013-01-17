package com.me.web.servlet.binding;

import com.me.web.servlet.Mapping;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import test.controller.FirstController;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class BinderTest {
    @Test
    public void test() {
        Binder<?> binder = Binder.binderOf(String.class.getName());
        assertThat(binder, CoreMatchers.notNullValue());

        Class<FirstController> clazz = FirstController.class;
        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            binder.get("name", request, new Mapping(clazz, clazz.getMethod("logic3", String.class)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        binder = Binder.binderOf(Object.class.getName());
        assertThat(binder, CoreMatchers.nullValue());
    }
}
