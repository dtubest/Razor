package com.me.web.servlet;

/**
 * User: t.ding
 * Date: 13-1-12
 */
public interface Result {
    /**
     * 返回http的响应状态
     *
     * @return 状态码
     */
    public int status();

    /**
     * 返回http的mime类型
     *
     * @return mime类型
     */
    public String contentType();

    /**
     * 返回实质上的内容
     *
     * @return 内容组成的byte数组
     */
    public byte[] wrappedContent();
}
