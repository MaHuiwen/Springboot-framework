package com.fzm.boot.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Author: fzm_cgl
 * @Date: 2018/5/31 14:30
 * @Description:  获得Md5加密后的数据，用于密码加密
 * @Version:1.0
 */
public class Md5Util {
    private static final Logger log = LoggerFactory.getLogger(Md5Util.class);

    public static String getMD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes(Charset.forName("utf-8")));
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte a : b) {
                i = a;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 " + e.getMessage());
            return "";
        }
    }

    /**
     * @Decription:
     * @param: salt 盐   inputPassword 要加密的密码
     * @return: 返回加密后的密码
     * @auther: fzm_cgl
     * @date: 2018/5/31 14:37
     */
    public static String getPassword(String salt, String inputPassword) {
        String pwd = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5", "SUN");
            BASE64Encoder b64Encoder = new BASE64Encoder();
            md.reset();
            md.update(salt.getBytes("UTF-8"));
            md.update(inputPassword.getBytes("UTF-8"));
            byte[] bys = md.digest();
            pwd = b64Encoder.encode(bys);
        } catch (Exception e) {
            log.error("password md5 encode " + e.getMessage());
        }

        return pwd;
    }

    /**
     * @Decription: 获取密码盐
     * @param:
     * @return: 指定长度的密码盐
     * @auther: fzm_cgl
     * @date: 2018/5/31 14:39
     */
    public static String getSalt() {
        byte[] salt = new byte[8];
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            salt[i] = (byte) ((rand.nextInt('~' - '!') + '!') & 0x007f);
        }
        return new String(salt).trim();
    }

}
