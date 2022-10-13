package com.cck.service;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;

public interface UserService {
    User findByName(String username);

    ResponseResult register(User user);

    ResponseResult findById(Integer id);

    ResponseResult edit(User user);

    ResponseResult checkPwd(User user);

    ResponseResult changePwd(User user);
}
