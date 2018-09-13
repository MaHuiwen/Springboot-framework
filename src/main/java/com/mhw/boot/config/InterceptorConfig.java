package com.mhw.boot.config;

import com.mhw.boot.handler.interceptor.RequestInterceptor;
import com.mhw.boot.service.UserService;
import com.mhw.boot.handler.interceptor.RequestInterceptor;
import com.mhw.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 *
 * @Author mhw_mhw
 * @Date 2018/6/22 19:11
 * @Version
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private UserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor(userService)).addPathPatterns("/**");
    }
}
