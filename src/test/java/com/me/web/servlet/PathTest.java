package com.me.web.servlet;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * User: t.ding
 * Date: 13-1-15
 */
public class PathTest {
    @Test
    public void test() {
        assertThat(Http.pathPattern.matcher("control").matches(), CoreMatchers.is(true));
        assertThat(Http.pathPattern.matcher("/control").matches(), CoreMatchers.is(true));
        assertThat(Http.pathPattern.matcher("control/").matches(), CoreMatchers.is(true));
        assertThat(Http.pathPattern.matcher("/control/").matches(), CoreMatchers.is(true));
        assertThat(Http.pathPattern.matcher("/control/aaa").matches(), CoreMatchers.is(true));

        assertThat(Http.pathPattern.matcher("/control/?fdsafd").matches(), CoreMatchers.is(false));
        assertThat(Http.pathPattern.matcher("/control/fdsafd//").matches(), CoreMatchers.is(false));
        assertThat(Http.pathPattern.matcher("/control/fds#afd/").matches(), CoreMatchers.is(false));
        assertThat(Http.pathPattern.matcher("/control/fds&afd/").matches(), CoreMatchers.is(false));
        assertThat(Http.pathPattern.matcher("/control/fds%afd/").matches(), CoreMatchers.is(false));
        assertThat(Http.pathPattern.matcher("/control/fds\\afd/").matches(), CoreMatchers.is(false));
        assertThat(Http.pathPattern.matcher("/control/fds+afd/").matches(), CoreMatchers.is(false));
        assertThat(Http.pathPattern.matcher("/").matches(), CoreMatchers.is(false));
    }
}
