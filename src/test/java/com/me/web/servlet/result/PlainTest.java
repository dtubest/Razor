package com.me.web.servlet.result;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;
/**
 * User: t.ding
 * Date: 13-1-24
 */
public class PlainTest {
    @Test
    public void test() {
        Result plain = new Plain(200, "i am content", Result.Type.DATA);
        assertThat(plain.status(), CoreMatchers.is(200));
        assertThat(plain.status(302).status(), CoreMatchers.is(302));
        assertThat(plain.status(500), CoreMatchers.is(plain));
    }
}
