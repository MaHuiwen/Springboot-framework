package com.mhw.boot.controller;

import com.mhw.boot.commons.bean.ResResult;
import com.mhw.boot.commons.util.ResResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试的Controller
 *
 * @Author mhw_mhw
 * @Date 2018/6/20 10:08
 * @Version
 */
@RestController
public class HelloWorld {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "test";
    }

    @PostMapping(value = "/say")
    public ResResult say(@RequestParam("sentence") String sentence) {
        return ResResultUtil.success(sentence);
    }
}
