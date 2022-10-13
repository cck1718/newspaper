package com.cck.service;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    ResponseResult findAll();
    ResponseResult save(Category category);
    ResponseResult delete(Integer id);

    ResponseResult findById(Category category);
}
