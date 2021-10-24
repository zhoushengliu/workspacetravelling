package com.atguigu.entity;

import java.io.Serializable;

/**
 * Result
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-26
 * @Description:
 *
 * Result:服务器返回数据给客户端
 */
public class Result implements Serializable {
    /**
     * 定义服务器和客户端交换是成功还是失败
     */
    private boolean flag;// 执行结果，true表示成功，false表示失败
    /**
     * 定义返回的消息
     */
    private String message;
    /**
     * 定义数据
     */
    private Object data;
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}