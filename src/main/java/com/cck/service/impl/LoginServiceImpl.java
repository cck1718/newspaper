package com.cck.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.security.LoginUser;
import com.cck.service.LoginService;
import com.cck.utils.JwtUtils;
import com.cck.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    //用户登录
    @Override
    public ResponseResult login(User user) {
        //获取AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证通过给出提示
        if (Objects.isNull(authenticate)) throw new RuntimeException("登录失败");
        //认证成功使用userid生成jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String token = JwtUtils.createToken(id);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("id",loginUser.getUser().getId().toString());
        //把完整用户信息存入redis
        RedisUtils.getRedisTemplate().opsForValue().set("login:"+id, loginUser);
//        System.out.println(RedisUtils.getRedisTemplate().opsForValue().get("login" + id));
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(),"登录成功",map);
    }


    //用户注销
    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer id = loginUser.getUser().getId();
        //删除redis中的值
        RedisUtils.getRedisTemplate().delete("login:"+id);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(),"注销成功");
    }
}
