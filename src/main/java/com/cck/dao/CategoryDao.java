package com.cck.dao;

import com.cck.domain.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {
    List<Category> selectAll();
    void delete(Integer id);
    void insert(Category category);
    void update(Category category);
    Category select(Category category);

}
