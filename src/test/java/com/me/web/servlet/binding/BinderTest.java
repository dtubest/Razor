package com.me.web.servlet.binding;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * User: t.ding
 * Date: 13-1-15
 */
public class BinderTest {
    @Test
    public void test() {
        Binder<?> binder = Binder.binderOf(String.class.getName());
        assertNotNull(binder);
    }
}
