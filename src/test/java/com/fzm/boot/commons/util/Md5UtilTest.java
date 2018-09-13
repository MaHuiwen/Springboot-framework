package com.mhw.boot.commons.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Md5UtilTest {

    private static final Logger log = LoggerFactory.getLogger(Md5UtilTest.class);

    @Test
    public void getMD5() {
        String password = "123456";
        String md5 = Md5Util.getMD5(password);
        log.info("md5加密后为： " + md5);
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void getSalt() {
    }
}