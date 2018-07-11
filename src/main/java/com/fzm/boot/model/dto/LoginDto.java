package com.fzm.boot.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class LoginDto {

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
