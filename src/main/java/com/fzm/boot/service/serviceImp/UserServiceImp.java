package com.fzm.boot.service.serviceImp;

import com.fzm.boot.commons.constant.ResponseEnum;
import com.fzm.boot.commons.exception.ServiceException;
import com.fzm.boot.commons.util.Md5Util;
import com.fzm.boot.commons.util.SnowFlakeUtil;
import com.fzm.boot.mapper.UserMapper;
import com.fzm.boot.mapper.UserTokenMapper;
import com.fzm.boot.model.dto.LoginDto;
import com.fzm.boot.model.dto.RegistryDto;
import com.fzm.boot.model.po.User;
import com.fzm.boot.model.po.UserToken;
import com.fzm.boot.model.vo.UserLoginVo;
import com.fzm.boot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 *
 *
 * @Author fzm_mhw
 * @Date 2018/6/20 16:32
 * @Version
 */
@Service
public class UserServiceImp implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    /**
     * 注册账户
     *
     * @param registryDto
     * @return 注册成功返回1，注册失败返回0 （是否需要返回VO）
     * @author fzm_mhw
     * @data 2018/6/22 11:41
     */
    @Override
    @Transactional
    public int registry(RegistryDto registryDto) {
        //获取盐值并对密码进行MD5加密
        String salt = Md5Util.getSalt();
        String password = Md5Util.getPassword(salt, registryDto.getPassword());

        //insert user表，用户编号通过雪花编号获取
        User user = new User();
        user.setId(SnowFlakeUtil.Snow.nextId());
        user.setUserTel(registryDto.getUserTel());
        user.setPassword(password);
        user.setSalt(salt);
        log.info(user.toString());
        userMapper.insert(user);

        //insert user_token表
        UserToken userToken = new UserToken();
        userToken.setUserId(user.getId());
        userToken.setToken(UUID.randomUUID().toString());
        userToken.setModifyTime(System.currentTimeMillis());
        userTokenMapper.insert(userToken);

        return 1;
    }

    /**
     * 校验手机号是否已经存在
     *
     * @param userTel 用户手机号
     * @return 手机号已经存在返回true，不存在存在false
     * @author fzm_mhw
     * @data 2018/6/22 11:44
     */
    @Override
    public boolean isTelExist(String userTel) {
        User list = userMapper.getByTel(userTel);
        return (list != null);
    }

    /**
     * 登陆，校验手机号和密码（是否需要返回VO）
     *
     * @param loginDto 登陆信息，包括手机号和密码
     * @return 校验成功返回UserLoginVo，失败返回null
     * @author fzm_mhw
     * @data 2018/6/22 14:27
     */
    @Override
    @Transactional
    public UserLoginVo login(LoginDto loginDto) {
        //获取传入的手机号和密码
        String userTel = loginDto.getUserTel();
        String password = loginDto.getPassword();

        //对密码加盐做MD5加密，再与数据库中的password比对。如果相等，则密码正确。
        User user = userMapper.getByTel(userTel);
        String salt = user.getSalt();
        String md5 = Md5Util.getPassword(salt, password);
        if(md5.equals(user.getPassword())) {
            //密码正确，更新user_token中的token值
            UserToken userToken = new UserToken();
            userToken.setUserId(user.getId());
            userToken.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
            userToken.setModifyTime(System.currentTimeMillis());
            userTokenMapper.updateByUserId(userToken);

            //返回UserLoginVo
            UserLoginVo userLoginVo = new UserLoginVo();
            userLoginVo.setUserId(user.getId());
            userLoginVo.setToken(userToken.getToken());
            return userLoginVo;
        } else {
            return null;
        }
    }

    /**
     * 通过用户id获取当前token值
     *
     * @param userId 用户编号
     * @return token
     * @author fzm_mhw
     * @data 2018/6/22 19:26
     */
    @Override
    public String getToken(Long userId) {
        UserToken userToken = userTokenMapper.getByUserId(userId);
        return userToken.getToken();
    }

    /**
     * 通过手机号查询用户信息
     *
     * @param tel
     * @return
     * @author fzm_mhw
     * @data 2018/7/6 12:35
     */
    @Override
    public User getByTel(String tel) {
        if(isTelExist(tel)) {
            return userMapper.getByTel(tel);
        }

        throw new ServiceException(ResponseEnum.TEL_NOT_EXIST_ERROR.getCode(), ResponseEnum.TEL_NOT_EXIST_ERROR.getMsg());
    }

}
