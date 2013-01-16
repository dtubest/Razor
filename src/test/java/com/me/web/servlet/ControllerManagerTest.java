package com.me.web.servlet;

import org.junit.Test;

import java.util.Set;

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
        for (Mapping mapping : mappings) {
            System.out.println("----------------" + mapping.uri);
        }
    }
}
