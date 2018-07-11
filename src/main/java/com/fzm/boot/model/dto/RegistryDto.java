package com.fzm.boot.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 注册传进的信息
 *
 * @Author fzm_mhw
 * @Date 2018/6/20 16:06
 * @Version
 */
@ApiModel(description = "注册信息")
public class RegistryDto {
    @ApiModelProperty(value = "手机号", name = "userTel")
    private String userTel;


    private String password;

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistryDto{" +
                "userTel=" + userTel +
                ", password='" + password + '\'' +
                '}';
    }
}
