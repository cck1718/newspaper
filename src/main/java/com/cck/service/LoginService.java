package com.cck.service;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
