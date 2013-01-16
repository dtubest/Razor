package com.me.utils;

import com.me.util.ClassUtils;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class ClassUtilsTest {
    @Test
    public void test() {
        assertNotNull(ClassUtils.loadClass("com.me.ConfigTest"));
    }

    @Test
    public void testGetSubPac() {

        System.out.println("separator : " + File.separator);

        URL result;
        ClassLoader loader = getClass().getClassLoader();
        String res = "com/me";
        result = loader.getResource(res);
        if (null == result)
            result = loader.getResource("/" + res);

        assertEquals("xxxxxxxxxxxxxxxx2222222222222222", 2, ClassUtils.getSubPackage("com.me").length);
    }
}
