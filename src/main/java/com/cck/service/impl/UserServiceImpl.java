package com.cck.service.impl;

import com.cck.dao.UserDao;
import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public ResponseResult register(User user) {
        user.setCreateTime(new Date());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userDao.insert(user);
        User user1 = userDao.findByName(user.getUsername());
        userDao.registerPermissions(user1.getId(),1);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }

    @Override
    public ResponseResult findById(Integer id) {
        User user = userDao.selectById(id);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg(),user);
    }

    @Override
    public ResponseResult edit(User user) {
        user.setUpdateTime(new Date());
        userDao.update(user);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }

    @Override
    public ResponseResult checkPwd(User user) {
        User checkUser = userDao.selectById(user.getId());
        if (Objects.isNull(checkUser))return new ResponseResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"id不存在");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(user.getPassword(),checkUser.getPassword()))return new ResponseResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"密码错误");
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(),AppHttpCodeEnum.SUCCESS.getMsg());
    }

    @Override
    public ResponseResult changePwd(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUpdateTime(new Date());
        userDao.changePwd(user);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }
}
