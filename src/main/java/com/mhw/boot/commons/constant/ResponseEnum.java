package com.mhw.boot.commons.constant;

/**
 * 响应状态码、信息 枚举类
 *
 * @Author mhw_mhw
 * @Date 2018/6/20 10:09
 * @Version
 */
public enum ResponseEnum {

    /**************通用异常**************/
    COMMONS_ERROR("系统错误", 500),
    COMMONS_SUCCESS("操作成功", 200),
    COMMONS_PARAM_ERROR("参数校验错误", -1),
    POST_SEND_ERROR("POST请求错误", -2),

    /**************Controller异常(1000起)**************/
    //1.用户模块(1000起)
    TEL_HAS_EXISTED("手机号已经存在",1000),
    TEL_NOT_EXIST("手机号不存在",1001),
    WRONG_PASSWORD("密码错误",1002),
    //2.系统模块(2000起)
    TOKEN_IS_TIMEOUT("登陆超时", 2000),



    /**************Service异常(10000起)**************/
    TEL_NOT_EXIST_ERROR("此手机号不存在",10000),



    /**************第三方异常(1000000)**************/
    FILE_EMPTY_ERROR("文件为空错误",100000),
    FILE_UPLOAD_ERROR("文件上传失败",100001),




    /**************最后一条枚举**************/
    LAST_ENUM("最后一条枚举",999999999);


    /***************构造信息***************/
    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private ResponseEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public static String getMsg(int code) {
        for(ResponseEnum responseEnum : ResponseEnum.values()) {
            if(responseEnum.getCode() == code) {
                return responseEnum.getMsg();
            }
        }
        return null;
    }
}
