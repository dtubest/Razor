package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 13-1-12
 */

/**
 * 这个类里面不定义action
 */
public class Results implements Result {
    // todo 这个类里面不定义action
    public static final Result TODO = new Result() {
        public int status() {
            return 501;
        }
    };
}
