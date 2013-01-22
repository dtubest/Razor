package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 13-1-12
 */

import com.me.web.servlet.result.NotFound;
import com.me.web.servlet.result.Ok;
import com.me.web.servlet.result.Todo;

/**
 * 这个类里面不定义action,只定义一系列的<code>Result</code>
 */
public final class Results {
    public static final Result OK = new Ok();

    public static final Result TODO = new Todo();

    public static final Result NOT_FOUND = new NotFound();
}
