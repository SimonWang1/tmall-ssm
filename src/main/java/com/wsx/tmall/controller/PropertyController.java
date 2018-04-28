package com.wsx.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.pojo.Property;
import com.wsx.tmall.service.CategoryService;
import com.wsx.tmall.service.PropertyService;
import com.wsx.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by frank on 2018/4/28.
 */

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PropertyService propertyService;

    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page){
        // 面包屑导航显示category属性
        Category category = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Property> properties = propertyService.list(cid);
        int total = (int) new PageInfo<>(properties).getTotal();
        page.setTotal(total);
        // 分页参数
        page.setParam("&cid=" + category.getId());
        model.addAttribute("properties", properties);
        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return "admin/listProperty";
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model, int id){
        Property property = propertyService.get(id);
        Category category = categoryService.get(property.getCid());
        property.setCategory(category);
        model.addAttribute("property", property);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property property){
        propertyService.update(property);
        return "redirect:admin_property_list?cid=" + property.getCid();
    }

    @RequestMapping("admin_property_add")
    public String add(Property property){
        propertyService.add(property);
        return "redirect:admin_property_list?cid=" + property.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id){
        Property property = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?cid=" + property.getCid();
    }
}
