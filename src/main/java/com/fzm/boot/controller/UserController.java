package com.fzm.boot.controller;

import com.alibaba.fastjson.JSON;
import com.fzm.boot.commons.bean.ResResult;
import com.fzm.boot.commons.constant.ResponseEnum;
import com.fzm.boot.commons.util.BeanCopyUtil;
import com.fzm.boot.commons.util.ResResultUtil;
import com.fzm.boot.model.dto.LoginDto;
import com.fzm.boot.model.dto.RegistryDto;
import com.fzm.boot.model.dto.UserDto;
import com.fzm.boot.model.po.User;
import com.fzm.boot.model.vo.UserLoginVo;
import com.fzm.boot.model.vo.UserVo;
import com.fzm.boot.service.UserService;
import com.fzm.boot.service.serviceImp.UserServiceImp;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户Controller层，注册、登陆
 *
 * @Author fzm_mhw
 * @Date 2018/6/20 13:40
 * @Version
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param registryDto
     * @return
     * @author fzm_mhw
     * @data 2018/6/20 16:22
     */
    @ApiOperation(value = "用户注册", notes = "")
    @ApiImplicitParam(name = "registryDto", value = "注册用户信息", required = true, dataType = "RegistryDto", paramType = "body")
    @PostMapping(value = "/registry")
    public ResResult registry(@RequestBody RegistryDto registryDto) {
        String userTel = registryDto.getUserTel();
        //true表示手机号已经存在，否则进行注册
        if(userService.isTelExist(userTel)) {
            return ResResultUtil.error(ResponseEnum.TEL_HAS_EXISTED);
        }
        userService.registry(registryDto);
        return ResResultUtil.success(null,"注册成功");
    }

    /**
     * 登陆。校验手机号和密码是否正确。如果正确，登陆成功，更改token值
     *
     * @param loginDto 包括
     * @return
     * @author fzm_mhw
     * @data 2018/6/22 15:34
     */
    @ApiOperation(value = "用户登陆", notes = "这是notes")
    @ApiImplicitParam(name = "loginDto", value = "用户登陆信息", required = true, dataType = "LoginDto", paramType = "body")
    @PostMapping(value = "/login")
    public ResResult login(@RequestBody LoginDto loginDto) {

        //校验手机号是否存在
        String userTel = loginDto.getUserTel();
        if(!userService.isTelExist(userTel)) {
            return ResResultUtil.error(ResponseEnum.TEL_NOT_EXIST);
        }

        //如果手机号存在，则校验密码是否正确.
        //如果密码正确，返回UserLoginVo
        UserLoginVo userLoginVo = userService.login(loginDto);
        if(userLoginVo != null) {
            return ResResultUtil.success(userLoginVo, "登陆成功");
        } else {
            return ResResultUtil.error(ResponseEnum.WRONG_PASSWORD);
        }
    }

    /**
     * 1.通过@ApiOperation注解来给API增加说明
     * 2.通过@ApiImplicitParams和@ApiImplicitParam注解来给参数增加说明
     *
     * @param userTel
     * @return ResResult
     * @author fzm_mhw
     * @data 2018/7/6 15:23
     */
    @ApiOperation(value = "获取用户详情", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "userTel", value = "查询的手机号", required = true, dataType = "String", paramType = "path")
    }
    )
    @PostMapping(value = "/info/{userTel}")
    public ResResult getInfo(@PathVariable String userTel) {
        log.info("/info/{userTel}接收参数：" + userTel);
        User user = userService.getByTel(userTel);
        UserVo userVo = (UserVo) BeanCopyUtil.beanCopy(user, UserVo.class);
        log.info("/info/{userTel}返回参数：" + JSON.toJSONString(userVo));
        return ResResultUtil.success(userVo);
    }
}
