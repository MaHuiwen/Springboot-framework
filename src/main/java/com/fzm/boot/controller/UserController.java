package com.fzm.boot.controller;

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
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param
     * @return
     * @author fzm_mhw
     * @data 2018/6/20 16:22
     */
    @ApiOperation(value = "用户注册", notes = "")
    @ApiImplicitParam(name = "registryDto", value = "注册用户信息", required = true, dataType = "RegistryDto")
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
    @ApiImplicitParam(name = "loginDto", value = "用户登陆信息", required = true, dataType = "LoginDto")
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
     * @param userDto
     * @return ResResult
     * @author fzm_mhw
     * @data 2018/7/6 15:23
     */
    @ApiOperation(value = "获取用户详情", notes = "")
    @ApiImplicitParam(name = "userDto", value = "用户详细实体user", required = true, dataType = "UserDto")
    @PostMapping(value = "/info")
    public ResResult getInfo(@RequestBody UserDto userDto) {
        String userTel = userDto.getUserTel();
        User user = userService.getByTel(userTel);
        UserVo userVo = (UserVo) BeanCopyUtil.beanCopy(user, UserVo.class);
        return ResResultUtil.success(userVo);
    }
}
