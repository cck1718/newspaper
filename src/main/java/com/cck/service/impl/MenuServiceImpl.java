package com.cck.service.impl;

import com.cck.dao.MenuDao;
import com.cck.domain.entity.Menu;
import com.cck.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<String> findByUserId(Integer id) {
        return menuDao.findByUserId(id);
    }
}
