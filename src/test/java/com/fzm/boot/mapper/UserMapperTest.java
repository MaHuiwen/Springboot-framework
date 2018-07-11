package com.fzm.boot.mapper;

import com.alibaba.fastjson.JSON;
import com.fzm.boot.model.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    private static final Logger log = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setId(123654664545L);
        user.setPassword("112233");
        user.setSalt("fdsf");
        user.setUserTel("15512345689");
        userMapper.insert(user);
    }

    @Test
    public void selectByTel() {
        String tel = "15512345678";
        User user = userMapper.getByTel(tel);
        if(user != null) {
            log.info(user.toString());
            log.info(JSON.toJSONString(user));
        }else {
            log.info("该手机号没有被使用");
        }
    }

    @Test
    public void insert1() {
    }
}