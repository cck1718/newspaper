package com.cck.controller;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;
import com.cck.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    //登录
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }
    //退出
    @RequestMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
