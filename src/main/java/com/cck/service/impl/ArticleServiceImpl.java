package com.cck.service.impl;

import com.cck.dao.ArticleDao;
import com.cck.domain.ResponseResult;
import com.cck.domain.entity.Article;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.security.LoginUser;
import com.cck.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public ResponseResult save(Article article) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer createBy = loginUser.getUser().getId();
        article.setCreateBy(createBy);
        article.setCreateTime(new Date());
        articleDao.insert(article);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }

    //接收作者id和文章状态，根据这两个属性查询作者的所有草稿或所有已发布文章
    @Override
    public ResponseResult findAllByUser(Article article) {
        List<Article> articles = articleDao.selectListByUser(article);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg(),articles);
    }

    @Override
    public ResponseResult edit(Article article) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer updateBy = loginUser.getUser().getId();
        article.setUpdateBy(updateBy);
        article.setUpdateTime(new Date());
        articleDao.update(article);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }

    @Override
    public ResponseResult del(Article article) {
        articleDao.delete(article);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }

    @Override
    public ResponseResult release(Article article) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer updateBy = loginUser.getUser().getId();
        article.setUpdateBy(updateBy);
        article.setUpdateTime(new Date());
        article.setStatus("0");
        articleDao.updateStatus(article);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
    }

    @Override
    public ResponseResult findByCategory(Article article) {
        List<Article> articles = articleDao.selectListCategory(article);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg(),articles);
    }

    @Override
    public ResponseResult findAll() {
        List<Article> articles = articleDao.selectList();
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg(),articles);
    }
}
