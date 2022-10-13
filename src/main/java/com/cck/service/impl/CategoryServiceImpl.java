package com.cck.service.impl;

import com.cck.dao.CategoryDao;
import com.cck.domain.ResponseResult;
import com.cck.domain.entity.Category;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    //查询所有类别信息
    @Override
    public ResponseResult findAll() {
        List<Category> categories = categoryDao.selectAll();
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg(),categories);
    }

    @Override
    public ResponseResult save(Category category) {
        if (ObjectUtils.isEmpty(category.getId())){
            categoryDao.insert(category);
        }else {
            categoryDao.update(category);
        }
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }

    @Override
    public ResponseResult delete(Integer id) {
        categoryDao.delete(id);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }

    @Override
    public ResponseResult findById(Category category) {
        Category category1 = categoryDao.select(category);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg(),category1);
    }
}
