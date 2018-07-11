package com.fzm.boot.commons.util;

import com.fzm.boot.commons.bean.ResResult;
import com.fzm.boot.commons.constant.ResponseEnum;

/**
 * 返回值工具类
 *
 * @Author fzm_mhw
 * @Date 2018/6/15 19:51
 * @Version
 */
public class ResResultUtil {

    /**
     * 返回数据和提示给前端的信息
     *
     * @param data
     * @param msg
     * @return ResResult
     */
    public static ResResult success(Object data, String msg) {
        ResResult result = new ResResult();
        result.setCode(ResponseEnum.COMMONS_SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 返回数据
     *
     * @param data
     * @return ResResult
     */
    public static ResResult success(Object data) {
        return success(data, null);
    }

    /**
     * 返回错误代码和信息
     *
     * @param code
     * @param msg
     * @return ResResult
     */
    public static ResResult error(Integer code, String msg) {
        ResResult result = new ResResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回错误代码和信息。入参为枚举。
     *
     * @param responseEnum 响应枚举
     * @return 错误码和错误信息
     * @author fzm_mhw
     * @data 2018/6/22 13:47
     */
    public static ResResult error(ResponseEnum responseEnum) {
        ResResult result = new ResResult();
        result.setCode(responseEnum.getCode());
        result.setMsg(responseEnum.getMsg());
        return result;
    }
}
