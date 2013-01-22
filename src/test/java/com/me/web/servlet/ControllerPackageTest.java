package com.me.web.servlet;

import com.me.web.servlet.config.ControllerPackage;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertThat;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class ControllerPackageTest {
    @Test
    public void testPattern() {
        String childPac = "[_$a-zA-Z][_$0-9a-zA-Z]*";
        Pattern pattern = Pattern.compile("^" + childPac + "(\\." + childPac + ")*$");

        assertThat(pattern.matcher("com.me").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("_com.me").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("$com.me").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("com4.me").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("com._me").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("com.$me").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("com.m4e").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("com.me4").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("com.me._").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("com.me.$").matches(), CoreMatchers.is(true));
        assertThat(pattern.matcher("com.me._4").matches(), CoreMatchers.is(true));

        assertThat(pattern.matcher(".com.me").matches(), CoreMatchers.is(false));
        assertThat(pattern.matcher("4com.me").matches(), CoreMatchers.is(false));
        assertThat(pattern.matcher("com.4me").matches(), CoreMatchers.is(false));
        assertThat(pattern.matcher("com.me.").matches(), CoreMatchers.is(false));
        assertThat(pattern.matcher("com.me.4").matches(), CoreMatchers.is(false));

    }

    @Test
    public void test() {
        ControllerPackage pac = new ControllerPackage();
        pac.addPackage("com.me");
        pac.addPackage("test.xxx");
        assertThat(pac.getPackages().length, CoreMatchers.is(2));

        pac.clear();
        assertThat("should be 0", pac.getPackages().length, CoreMatchers.is(0));

    }


}
