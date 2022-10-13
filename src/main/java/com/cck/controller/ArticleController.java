package com.cck.controller;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.Article;
import com.cck.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //添加文章 草稿或发布
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('release')")
    public ResponseResult save(@RequestBody Article article){
        return articleService.save(article);
    }

    //查看文章 草稿或发布
    @PostMapping("/findAllByUser")
    @PreAuthorize("hasAuthority('release')")
    public ResponseResult findAllByUser(@RequestBody Article article){
        return articleService.findAllByUser(article);
    }

    //修改文章
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('content')")
    public ResponseResult edit(@RequestBody Article article){
        return articleService.edit(article);
    }

    //删除文章
    @PostMapping("/del")
    @PreAuthorize("hasAuthority('content')")
    public ResponseResult del(@RequestBody Article article){
        return articleService.del(article);
    }

    //发布文章
    @PostMapping("/release")
    @PreAuthorize("hasAuthority('release')")
    public ResponseResult release(@RequestBody Article article){
        return articleService.release(article);
    }

    //查询所有文章
    @PostMapping("/findAll")
    @PreAuthorize("hasAuthority('content')")
    public ResponseResult findAll(){
        return articleService.findAll();
    }

    //根据类别查询文章
    @PostMapping("/findByCategory")
    @PreAuthorize("hasAuthority('content')")
    public ResponseResult findByCategory(@RequestBody Article article){
        return articleService.findByCategory(article);
    }
}
