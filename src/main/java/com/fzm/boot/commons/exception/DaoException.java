package com.fzm.boot.commons.exception;

/**
 * dao层发生的异常
 *
 * @Author fzm_mhw
 * @Date 2018/6/20 10:05
 * @Version
 */
public class DaoException extends RuntimeException{

    private Integer code;

    public DaoException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public DaoException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
