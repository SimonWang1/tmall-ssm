package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.OrderMapper;
import com.wsx.tmall.pojo.Order;
import com.wsx.tmall.pojo.OrderExample;
import com.wsx.tmall.pojo.User;
import com.wsx.tmall.service.OrderService;
import com.wsx.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frank on 2018/5/4.
 */

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id asc");
        List<Order> results = orderMapper.selectByExample(example);
        // 获取order对应user用于前端显示
        setUser(results);
        return results;
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    public void setUser(List<Order> orders){
        // 遍历调用单条
        for(Order order : orders){
            setUser(order);
        }
    }

    // 设置order对应user属性
    public void setUser(Order order){
        int uid = order.getUid();
        User user = userService.get(uid);
        order.setUser(user);
    }
}
