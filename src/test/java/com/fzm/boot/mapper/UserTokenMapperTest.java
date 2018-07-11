package com.fzm.boot.mapper;

import com.alibaba.fastjson.JSON;
import com.fzm.boot.model.po.UserToken;
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
public class UserTokenMapperTest {

    private static final Logger log = LoggerFactory.getLogger(UserTokenMapperTest.class);

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Test
    public void insert() {
        UserToken userToken = new UserToken();
        userToken.setUserId(123564L);
        userToken.setToken("132");
        userToken.setModifyTime(System.currentTimeMillis());
        log.info(userToken.toString());
        userTokenMapper.insert(userToken);
    }

    @Test
    public void updateByUserId() {
        UserToken userToken = new UserToken();
        userToken.setUserId(123564L);
        userToken.setToken("156");
        userToken.setModifyTime(System.currentTimeMillis());
        log.info(userToken.toString());
        userTokenMapper.updateByUserId(userToken);
    }

    @Test
    public void getByUserId() {
        Long userId = 3167119154600L;
        UserToken userToken = userTokenMapper.getByUserId(userId);
        log.info(JSON.toJSONString(userToken));
    }
}