package com.fzm.boot.service;

import com.fzm.boot.model.dto.LoginDto;
import com.fzm.boot.model.dto.RegistryDto;
import com.fzm.boot.model.po.User;
import com.fzm.boot.model.vo.UserLoginVo;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @Author fzm_mhw
 * @Date 2018/6/20 18:55
 * @Version
 */
public interface UserService {

    /**
     * 注册账户
     *
     * @param registryDto
     * @return 注册成功返回1，注册失败返回0 （是否需要返回VO）
     * @author fzm_mhw
     * @data 2018/6/22 11:41
     */
    int registry(RegistryDto registryDto);

    /**
     * 校验手机号是否已经存在
     *
     * @param userTel 用户手机号
     * @return 手机号已经存在返回true，不存在存在false
     * @author fzm_mhw
     * @data 2018/6/22 11:44
     */
    boolean isTelExist(String userTel);

    /**
     * 登陆，校验手机号和密码。
     *
     * @param loginDto 登陆信息，包括手机号和密码
     * @return 校验成功返回UserLoginVo，失败返回null
     * @author fzm_mhw
     * @data 2018/6/22 14:27
     */
    UserLoginVo login(LoginDto loginDto);

    /**
     * 通过用户id获取当前token值
     *
     * @param userId 用户编号
     * @return token
     * @author fzm_mhw
     * @data 2018/6/22 19:26
     */
    String getToken(Long userId);

    /**
     * 通过手机号查询用户信息
     *
     * @param tel
     * @return User
     * @author fzm_mhw
     * @data 2018/7/6 12:35
     */
    User getByTel(String tel);

    /**
     * 更新所用用户的时间
     *
     * @param
     * @return
     * @author fzm_mhw
     * @date 2018/8/20 10:51
     */
    void updateTime() throws JobExecutionException;
}
