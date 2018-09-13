package com.mhw.boot.commons.exception;

/**
 * 第三方接口异常
 *
 * @Author mhw_mhw
 * @Date 2018/6/20 10:10
 * @Version
 */
public class ThirdPartyException extends RuntimeException{

    private Integer code;

    public ThirdPartyException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public ThirdPartyException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
