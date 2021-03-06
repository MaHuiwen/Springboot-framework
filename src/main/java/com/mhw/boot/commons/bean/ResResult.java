package com.mhw.boot.commons.bean;

/**
 * Http响应结果封装类
 *
 * @Author mhw_mhw
 * @Date 2018/6/15 19:49
 * @Version
 */
public class ResResult<T> {
    /**错误码*/
    private Integer code;

    /**错误信息*/
    private String msg;

    /**返回的数据*/
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
