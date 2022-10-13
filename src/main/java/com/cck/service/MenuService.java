package com.cck.service;

import java.util.List;

public interface MenuService {
    List<String> findByUserId(Integer id);
}
