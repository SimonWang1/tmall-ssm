package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.CategoryMapper;
import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.service.CategoryService;
import com.wsx.tmall.util.Page;
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
    public List<Category> list(Page page) {
        return categoryMapper.list(page);
    }

    @Override
    public int total() {
        return categoryMapper.total();
    }
}
