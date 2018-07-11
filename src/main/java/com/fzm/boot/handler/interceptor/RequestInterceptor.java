package com.fzm.boot.handler.interceptor;

import com.fzm.boot.commons.constant.ResponseEnum;
import com.fzm.boot.commons.exception.ControllerException;
import com.fzm.boot.mapper.UserMapper;
import com.fzm.boot.mapper.UserTokenMapper;
import com.fzm.boot.model.po.User;
import com.fzm.boot.model.po.UserToken;
import com.fzm.boot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截请求，主要是做token验证
 *
 * @Author fzm_mhw
 * @Date 2018/6/22 16:52
 * @Version
 */
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    private static final String LOGIN_URI = "/login";
    private static final String REGISTRY_URI = "/registry";
    private static final String SWAGGER_UI = "swagger-ui.html";

    private UserService userService;

    public RequestInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器");
        String uri = request.getRequestURI();
        Long userId = 3244643567464L;
        if(!(uri.endsWith(LOGIN_URI) || uri.endsWith(REGISTRY_URI) || uri.contains("swagger") || uri.contains("api-docs"))) {
            String token = request.getHeader("token");
            String currentToken = userService.getToken(userId);
            //如果不是注册或者登陆，就要校验token
            if(token == null || !token.equals(currentToken)) {
                System.out.println("uri: " + uri);
                throw new ControllerException(ResponseEnum.TOKEN_IS_TIMEOUT.getCode(), ResponseEnum.TOKEN_IS_TIMEOUT.getMsg());
            }
        }
        log.info("拦截结束，返回true");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
