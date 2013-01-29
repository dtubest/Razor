package com.me.web.servlet.result;

/**
 * User: t.ding
 * Date: 13-1-23
 */
class MutableResult implements Result {
    private int status;
    private String content;
    private Type type;

    public MutableResult() {
    }

    public MutableResult(int status, String content, Type type) {
        this.status = status;
        this.content = content;
        this.type = type;
    }

    @Override
    public int status() {
        return status;
    }

    /**
     * 修改状态码，并返回自己<br/>
     * 注意：当type为REDIRECT时，无法修改状态
     *
     * @param status 状态码
     * @return this
     */
    public MutableResult status(int status) {
        if (type() != Type.REDIRECT)
            this.status = status;
        return this;
    }

    @Override
    public Type type() {
        return type;
    }

//    /**
//     * 修改类型，并返回自己
//     *
//     * @param type 类型
//     * @return this
//     */
//    public MutableResult type(Type type) {
//        this.type = type;
//        return this;
//    }

    @Override
    public String content() {
        return content;
    }

    /**
     * 修改内容，并返回自己
     *
     * @param content 内容
     * @return this
     */
    public MutableResult content(String content) {
        this.content = content;
        return this;
    }
}
