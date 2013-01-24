package com.me.web.servlet;

import com.me.web.servlet.result.Result;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;
import test.controller.FirstController;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertThat;

/**
 * User: t.ding
 * Date: 13-1-17
 */
public class DispatcherTest {
    @Test
    public void test() throws NoSuchMethodException {
        Dispatcher dispatcher = new Dispatcher();
        Class<FirstController> control = FirstController.class;

        Mapping mapping = new Mapping(control, control.getMethod("logic3", String.class));
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Result service = dispatcher.service(mapping, request);

        assertThat(service, CoreMatchers.nullValue());
    }
}
