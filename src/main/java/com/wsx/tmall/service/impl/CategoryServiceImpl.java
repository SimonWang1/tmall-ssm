package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.CategoryMapper;
import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frank on 2018/4/24.
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }
}
