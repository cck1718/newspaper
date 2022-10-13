package com.cck.dao;

import com.cck.domain.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    List<String> findByUserId(Integer id);
}
