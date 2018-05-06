package com.wsx.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsx.tmall.pojo.Order;
import com.wsx.tmall.service.OrderItemService;
import com.wsx.tmall.service.OrderService;
import com.wsx.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by frank on 2018/5/4.
 */

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping("admin_order_list")
    public String list(Page page, Model model) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Order> orders = orderService.list();
        int total = (int) new PageInfo<>(orders).getTotal();
        page.setTotal(total);
        // 通过fill(orders)依次设置orderItem参数
        orderItemService.fill(orders);
        model.addAttribute("page", page);
        model.addAttribute("orders", orders);
        return "admin/listOrder";
    }

    @RequestMapping("admin_order_delivery")
    // 点击发货按钮时触发
    public String delivery(Order order) throws IOException {
        // 修改deliveryDate和status参数
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:admin_order_list";
    }
}
