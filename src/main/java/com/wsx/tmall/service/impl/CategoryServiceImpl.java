package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.CategoryMapper;
import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.pojo.CategoryExample;
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
    public List<Category> list() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("id asc");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
