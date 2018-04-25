package com.wsx.tmall.controller;

import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.service.CategoryService;
import com.wsx.tmall.util.Page;
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
    public String list(Model model, Page page) {
        List<Category> categories = categoryService.list(page);
        int total = categoryService.total();
        page.setTotal(total);
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }
}
