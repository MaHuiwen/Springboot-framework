package com.mhw.boot.controller;

import com.mhw.boot.model.dto.RegistryDto;
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
public class UserControllerTest {

    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    /**
     * Controller的单元测试如何写？
     *
     * @param
     * @return
     * @author mhw_mhw
     * @data 2018/6/22 13:55
     */
    @Test
    public void registry() {
        RegistryDto registryDto = new RegistryDto();
        registryDto.setUserTel("133156478");
        registryDto.setPassword("123456");
        log.info(registryDto.toString());

    }
}