package com.fzm.boot.commons.exception;

/**
 * service层发生的异常
 *
 * @Author fzm_mhw
 * @Date 2018/6/20 10:05
 * @Version
 */
public class ServiceException extends RuntimeException{

    private Integer code;

    public ServiceException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
