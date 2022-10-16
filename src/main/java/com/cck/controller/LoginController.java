package com.cck.controller;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;
import com.cck.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags="登录注销接口描述")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //登录
    @PostMapping("/login")
    @ApiOperation("登录接口")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }
    //退出
    @RequestMapping("/logout")
    @ApiOperation("退出登录接口")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
