package com.mhw.boot.service.serviceImp;

import com.alibaba.fastjson.JSON;
import com.mhw.boot.model.dto.LoginDto;
import com.mhw.boot.model.dto.RegistryDto;
import com.mhw.boot.model.po.User;
import com.mhw.boot.model.vo.UserLoginVo;
import com.mhw.boot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImpTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void registry() {
        RegistryDto registryDto = new RegistryDto();
        registryDto.setUserTel("15911223456");
        registryDto.setPassword("123");
        log.info(registryDto.toString());
        int result = userService.registry(registryDto);
        log.info("result = " + result);
    }

    @Test
    public void isTelExist() {
        String tel = "15512345678";
        boolean result = userService.isTelExist(tel);
        log.info("是否已经存在重复的手机号： " + result);

    }

    @Test
    public void login() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserTel("15911223456");
        loginDto.setPassword("123");
        UserLoginVo result = userService.login(loginDto);
        log.info("结果为： " + JSON.toJSONString(result));
    }

    @Test
    public void getToken() {
        Long userId = 3167119154600L;
        String token = userService.getToken(userId);
        log.info("token = " + token);
    }

    @Test
    public void getByTel() {
        String tel = "15512345678";
        User user = userService.getByTel(tel);
        log.info(JSON.toJSONString(user));
    }
}