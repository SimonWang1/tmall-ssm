package com.wsx.tmall.controller;

import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by frank on 2018/4/24.
 */

@Controller
@RequestMapping("")
public class CategoryControlller {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model) {
        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "admin/listCategory";
    }
}
