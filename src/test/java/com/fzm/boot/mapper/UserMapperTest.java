package com.mhw.boot.mapper;

import com.alibaba.fastjson.JSON;
import com.mhw.boot.commons.util.SnowFlakeUtil;
import com.mhw.boot.model.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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
    public void testInsertUserList() {
        for(int j = 1; j <= 100 ; j++) {
            List<User> list = new ArrayList<>();
            for(int i = 1; i< 1000; i++) {
                User user = new User();
                user.setId(SnowFlakeUtil.Snow.nextId());
                long modifyTime = (long) (System.currentTimeMillis()
                        + Math.random() * 10000000000L);
                user.setModifyTime(modifyTime);
                user.setStatus(0);
                if(modifyTime > System.currentTimeMillis() + 10000000000L/2) {
                    user.setStatus(1);
                }
                list.add(user);
            }
            userMapper.insertUserList(list);
        }

    }
}