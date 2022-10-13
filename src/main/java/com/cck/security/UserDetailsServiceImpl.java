package com.cck.security;

import com.cck.domain.entity.User;
import com.cck.service.MenuService;
import com.cck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询用户信息
        User user = userService.findByName(s);
        //查询结果校验
        if (Objects.isNull(user)) throw new UsernameNotFoundException("用户名或密码查询错误");
        //查询权限信息
        List<String> list = menuService.findByUserId(user.getId());
        //把数据封装成UserDetails类返回
        return new LoginUser(user,list);
    }
}
