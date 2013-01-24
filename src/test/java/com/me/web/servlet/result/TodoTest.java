package com.me.web.servlet.result;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;
/**
 * User: t.ding
 * Date: 13-1-24
 */
public class TodoTest {

    @Test
    public void test() {
        Todo todo = new Todo();
        assertThat(todo.status(), CoreMatchers.is(501));
        assertThat(todo.type(), CoreMatchers.equalTo(Result.Type.DATA));
    }
}
