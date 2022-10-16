package com.cck.controller;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.Article;
import com.cck.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@Api(tags="文章接口描述")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //添加文章 草稿或发布
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('release')")
    @ApiOperation(value = "添加文章接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据status属性判断是否发布")
    public ResponseResult save(@RequestBody Article article){
        return articleService.save(article);
    }

    //查看文章 草稿或发布
    @PostMapping("/findAllByUser")
    @PreAuthorize("hasAuthority('release')")
    @ApiOperation(value = "查看文章接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据status属性判断查看发布文章或者草稿")
    public ResponseResult findAllByUser(@RequestBody Article article){
        return articleService.findAllByUser(article);
    }

    //修改文章
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('content')")
    @ApiOperation("修改文章接口")
    public ResponseResult edit(@RequestBody Article article){
        return articleService.edit(article);
    }

    //删除文章
    @PostMapping("/del")
    @PreAuthorize("hasAuthority('content')")
    @ApiOperation(value = "删除文章接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据id属性删除文章")
    public ResponseResult del(@RequestBody Article article){
        return articleService.del(article);
    }

    //发布文章
    @PostMapping("/release")
    @PreAuthorize("hasAuthority('release')")
    @ApiOperation("发布文章接口")
    public ResponseResult release(@RequestBody Article article){
        return articleService.release(article);
    }

    //查询所有文章
    @PostMapping("/findAll")
    @PreAuthorize("hasAuthority('content')")
    @ApiOperation("查询所有文章接口")
    public ResponseResult findAll(){
        return articleService.findAll();
    }

    //根据类别查询文章
    @PostMapping("/findByCategory")
    @PreAuthorize("hasAuthority('content')")
    @ApiOperation(value = "按类别查询文章接口",notes = "<span style='color:red'>描述：</span>&nbsp;根据categoryId属性查询文章")
    public ResponseResult findByCategory(@RequestBody Article article){
        return articleService.findByCategory(article);
    }
}
