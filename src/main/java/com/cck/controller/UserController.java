package com.cck.controller;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.service.UserService;
import com.cck.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

    //查询用户是否被注册
    @PostMapping("/findName")
    public ResponseResult findName(@RequestBody User user){
        User userByName = userService.findByName(user.getUsername());
        if (Objects.isNull(userByName)) return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
        return new ResponseResult(AppHttpCodeEnum.USERNAME_EXIST.getCode(),AppHttpCodeEnum.USERNAME_EXIST.getMsg());
    }

    //根据id查询用户信息
    @PostMapping("/findById")
    @PreAuthorize("hasAuthority('personal')")
    public ResponseResult findById(@RequestBody User user){
        return userService.findById(user.getId());
    }

    //修改信息
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('personal')")
    public ResponseResult edit(@RequestBody User user){
        return userService.edit(user);
    }

    //检查密码
    @PostMapping("/checkPwd")
    @PreAuthorize("hasAuthority('personal')")
    public ResponseResult checkPwd(@RequestBody User user){
        return userService.checkPwd(user);
    }

    //修改密码
    @PostMapping("/changePwd")
    @PreAuthorize("hasAuthority('personal')")
    public ResponseResult changePwd(@RequestBody User user){
        return userService.changePwd(user);
    }
}
