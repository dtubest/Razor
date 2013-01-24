package com.me.web.servlet.result;

/**
 * User: t.ding
 * Date: 13-1-12
 */
public interface Result {
    /**
     * 获取当前状态码
     *
     * @return 状态码
     */
    public int status();

    /**
     * 修改状态码，并返回自己<br/>
     * 注意：当type为REDIRECT时，无法修改状态
     *
     * @param status 状态码
     * @return this
     */
    public Result status(int status);

    /**
     * 获取结果类型
     *
     * @return 类型
     */
    public Type type();

    /**
     * 修改类型，并返回自己
     *
     * @param type 类型
     * @return this
     */
    public Result type(Type type);

    /**
     * 获取content<br />
     * 当type为VIEW时，content为视图名，type为DATA时，content为数据本身
     *
     * @return 根据type的不同为视图名或者数据本身
     */
    public String content();

    /**
     * 修改内容，并返回自己
     *
     * @param content 内容
     * @return this
     */
    public Result content(String content);

    public static enum Type {
        VIEW, DATA, REDIRECT
    }
}
