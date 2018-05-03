package com.wsx.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsx.tmall.pojo.User;
import com.wsx.tmall.service.UserService;
import com.wsx.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by frank on 2018/5/3.
 */

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("admin_user_list")
    public String list(Page page, Model model){
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<User> users = userService.list();
        int total = (int) new PageInfo<User>().getTotal();
        page.setTotal(total);
        model.addAttribute("users", users);
        model.addAttribute("page", page);
        return "admin/listUser";
    }
}
