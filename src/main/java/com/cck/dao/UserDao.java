package com.cck.dao;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User findByName(String username);

    void insert(User user);
    User selectById(Integer id);
    void update(User user);
    void changePwd(User user);
    void registerPermissions(Integer userId, Integer roleId);
}
