package com.me.web.servlet;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class ControllerPackageTest {
    @Test
    public void testPattern() {
        String childPac = "[_$a-zA-Z][_$0-9a-zA-Z]*";
        Pattern pattern = Pattern.compile("^" + childPac + "(\\." + childPac + ")*$");

        assertTrue(pattern.matcher("com.me").matches());
        assertTrue(pattern.matcher("_com.me").matches());
        assertTrue(pattern.matcher("$com.me").matches());
        assertTrue(pattern.matcher("com4.me").matches());
        assertTrue(pattern.matcher("com._me").matches());
        assertTrue(pattern.matcher("com.$me").matches());
        assertTrue(pattern.matcher("com.m4e").matches());
        assertTrue(pattern.matcher("com.me4").matches());
        assertTrue(pattern.matcher("com.me._").matches());
        assertTrue(pattern.matcher("com.me.$").matches());
        assertTrue(pattern.matcher("com.me._4").matches());

        assertFalse(pattern.matcher(".com.me").matches());
        assertFalse(pattern.matcher("4com.me").matches());
        assertFalse(pattern.matcher("com.4me").matches());
        assertFalse(pattern.matcher("com.me.").matches());
        assertFalse(pattern.matcher("com.me.4").matches());

    }

    @Test
    public void test() {
        ControllerPackage pac = new ControllerPackage();
        pac.addPackage("com.me");

        pac.addPackage("test.xxx");
        assertEquals("shout only has 2 pac", 2, pac.getPackages().length);

        pac.clear();
        assertEquals("shout only has 0 pac", 0, pac.getPackages().length);

    }


}
