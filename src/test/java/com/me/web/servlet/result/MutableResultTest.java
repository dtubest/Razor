package com.me.web.servlet.result;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * User: t.ding
 * Date: 13-1-24
 */
public class MutableResultTest {
    @Test
    public void test() {
        Result plain = new MutableResult(200, "i am content", Result.Type.DATA);
        assertThat(plain.status(), CoreMatchers.is(200));
    }
}
