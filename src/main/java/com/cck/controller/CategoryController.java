package com.cck.controller;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.Category;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //查询所有类别
    @PostMapping("/findAll")
    public ResponseResult findAll(){
        return categoryService.findAll();
    }
    //添加类别 修改类别
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('edit')")
    public ResponseResult save(Category category){
        return categoryService.save(category);
    }

    //删除类别
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('edit')")
    public ResponseResult delete(Integer id){
        return categoryService.delete(id);
    }

    //根据id获取类别
    @PostMapping("/findById")
    public ResponseResult findById(@RequestBody Category category){
        return categoryService.findById(category);
    }
}
