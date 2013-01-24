package com.me.utils;

import com.me.util.ClassUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class ClassUtilsTest {
    @Test
    public void test() {
        assertThat(ClassUtils.loadClass("com.me.ConfigTest"), CoreMatchers.notNullValue());
    }
}
