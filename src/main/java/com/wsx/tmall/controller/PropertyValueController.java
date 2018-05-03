package com.wsx.tmall.controller;

import com.wsx.tmall.pojo.Product;
import com.wsx.tmall.pojo.PropertyValue;
import com.wsx.tmall.service.ProductService;
import com.wsx.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by frank on 2018/5/3.
 */

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    private ProductService productService;

    @Autowired
    private PropertyValueService propertyValueService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(int pid, Model model){
        // 产品对象引用用于面包屑导航
        Product product = productService.get(pid);
        // 初始化
        propertyValueService.init(product);
        // 查询属性值，包含属性值对应属性名称
        List<PropertyValue> pvs = propertyValueService.list(product.getId());
        model.addAttribute("product", product);
        model.addAttribute("pvs", pvs);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    // AJAX异步响应更新
    public String update(PropertyValue propertyValue){
        propertyValueService.update(propertyValue);
        return "success";
    }
}
