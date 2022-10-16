package com.cck.controller;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
@Api(tags="用户接口描述")
public class UserController{

    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    @ApiOperation("用户注册接口")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

    //查询用户是否被注册
    @PostMapping("/findName")
    @ApiOperation(value = "查询用户是否被注册接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据username属性查询该用户名是否已经被注册")
    public ResponseResult findName(@RequestBody User user){
        User userByName = userService.findByName(user.getUsername());
        if (Objects.isNull(userByName)) return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
        return new ResponseResult(AppHttpCodeEnum.USERNAME_EXIST.getCode(),AppHttpCodeEnum.USERNAME_EXIST.getMsg());
    }

    //根据id查询用户信息
    @PostMapping("/findById")
    @PreAuthorize("hasAuthority('personal')")
    @ApiOperation(value = "用户查询自身信息接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据id查询用户的个人信息")
    public ResponseResult findById(@RequestBody User user){
        return userService.findById(user.getId());
    }

    //修改信息
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('personal')")
    @ApiOperation("修改用户信息接口")
    public ResponseResult edit(@RequestBody User user){
        return userService.edit(user);
    }

    //检查密码
    @PostMapping("/checkPwd")
    @PreAuthorize("hasAuthority('personal')")
    @ApiOperation(value = "检查用户密码是否正确接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据id和password属性判断当前用户输入的密码是否正确")
    public ResponseResult checkPwd(@RequestBody User user){
        return userService.checkPwd(user);
    }

    //修改密码
    @PostMapping("/changePwd")
    @PreAuthorize("hasAuthority('personal')")
    @ApiOperation(value = "修改用户密码接口",notes = "<span style='color:red'>描述：</span>&nbsp;上传id和新的password数据修改密码")
    public ResponseResult changePwd(@RequestBody User user){
        return userService.changePwd(user);
    }
}
