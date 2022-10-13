package com.cck.dao;

import com.cck.domain.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {

    void insert(Article article);
    List<Article> selectListByUser(Article article);
    void update(Article article);
    void delete(Article article);
    void updateStatus(Article article);
    List<Article> selectList();
    List<Article> selectListCategory(Article article);
}
