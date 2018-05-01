package com.wsx.tmall.service;

import com.wsx.tmall.pojo.Category;

import java.util.List;

/**
 * Created by frank on 2018/4/24.
 */
public interface CategoryService {
    List<Category> list();

    Category get(int id);

    void update(Category category);

    void add(Category category);

    void delete(int id);
}
