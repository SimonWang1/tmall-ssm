package com.wsx.tmall.mapper;

import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.util.Page;

import java.util.List;

/**
 * Created by frank on 2018/4/24.
 */
public interface CategoryMapper {
    List<Category> list(Page page);

    int total();
}
