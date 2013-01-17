package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 13-1-12
 */

/**
 * 这个类里面不定义action
 */
public class Results implements Result {
    public static final Result OK = new Result() {
        public int status() {
            return 200;
        }
    };

    public static final Result TODO = new Result() {
        public int status() {
            return 501;
        }
    };
}
