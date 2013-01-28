package com.me.web.servlet.result;

/**
 * User: t.ding
 * Date: 13-1-12
 */
public interface Result {
    /**
     * 获取当前http状态码
     *
     * @return http状态码
     */
    public int status();

    /**
     * 获取结果类型
     *
     * @return 类型
     */
    public Type type();

    /**
     * 获取content<br />
     * 当type为VIEW时，content为视图名，type为DATA时，content为数据本身
     *
     * @return 根据type的不同为视图名或者数据本身
     */
    public String content();

    public static enum Type {
        VIEW, DATA, REDIRECT
    }
}
