package com.cck.service;

import com.cck.domain.ResponseResult;
import com.cck.domain.entity.Article;

public interface ArticleService {
    ResponseResult save(Article article);

    ResponseResult findAllByUser(Article article);

    ResponseResult edit(Article article);

    ResponseResult del(Article article);

    ResponseResult release(Article article);

    ResponseResult findByCategory(Article article);

    ResponseResult findAll();
}
