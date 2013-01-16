package com.me.web.servlet;

import com.me.web.servlet.annotation.Path;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class PathTest {
    @Test
    public void test() {
        assertTrue(Http.pathPattern.matcher("control").matches());
        assertTrue(Http.pathPattern.matcher("/control").matches());
        assertTrue(Http.pathPattern.matcher("control/").matches());
        assertTrue(Http.pathPattern.matcher("/control/").matches());
        assertTrue(Http.pathPattern.matcher("/control/aaa").matches());

        assertFalse(Http.pathPattern.matcher("/control/?fdsafd").matches());
        assertFalse(Http.pathPattern.matcher("/control/fdsafd//").matches());
        assertFalse(Http.pathPattern.matcher("/control/fds#afd/").matches());
        assertFalse(Http.pathPattern.matcher("/control/fds&afd/").matches());
        assertFalse(Http.pathPattern.matcher("/control/fds%afd/").matches());
        assertFalse(Http.pathPattern.matcher("/control/fds\\afd/").matches());
        assertFalse(Http.pathPattern.matcher("/control/fds+afd/").matches());
        assertFalse(Http.pathPattern.matcher("/").matches());
    }
}
