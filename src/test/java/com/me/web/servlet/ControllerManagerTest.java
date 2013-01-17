package com.me.web.servlet;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertThat;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class ControllerManagerTest {
    @Test
    public void test() {
        ControllerManager manager = new ControllerManager();
        manager.regControllersByPackage("test.controller");
        Set<Mapping> mappings = manager.getMappings();
        assertThat(mappings.size(), CoreMatchers.is(1));

        Mapping[] matches = manager.matches("/First/hello");
        assertThat(matches.length, CoreMatchers.is(0));
    }
}
