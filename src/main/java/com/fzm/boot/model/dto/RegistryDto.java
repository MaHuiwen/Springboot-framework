package com.fzm.boot.model.dto;

/**
 * 注册传进的信息
 *
 * @Author fzm_mhw
 * @Date 2018/6/20 16:06
 * @Version
 */
public class RegistryDto {
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
