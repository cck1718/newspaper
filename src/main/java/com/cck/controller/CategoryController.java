package com.cck.controller;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.Category;
import com.cck.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
@Api(tags="文章类别接口描述")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //查询所有类别
    @PostMapping("/findAll")
    @ApiOperation("查询所有类比接口")
    public ResponseResult findAll(){
        return categoryService.findAll();
    }
    //添加类别 修改类别
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('edit')")
    @ApiOperation(value = "保存类别信息接口",notes = "<span style='color:red'>描述：</span>&nbsp;上传类别信息，根据id是否为null判断此次操作时更新还是新增")
    public ResponseResult save(Category category){
        return categoryService.save(category);
    }

    //删除类别
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('edit')")
    @ApiOperation(value = "删除类别接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据id属性删除类别信息")
    public ResponseResult delete(Integer id){
        return categoryService.delete(id);
    }

    //根据id获取类别
    @PostMapping("/findById")
    @ApiOperation(value = "类别信息获取接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据类别id查询单个类别信息")
    public ResponseResult findById(@RequestBody Category category){
        return categoryService.findById(category);
    }
}
