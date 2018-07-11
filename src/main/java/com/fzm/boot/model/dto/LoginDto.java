package com.fzm.boot.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class LoginDto {
    @ApiModelProperty
    private String userTel;

    private String password;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
