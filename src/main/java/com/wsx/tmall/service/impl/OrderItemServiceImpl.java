package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.OrderItemMapper;
import com.wsx.tmall.pojo.Order;
import com.wsx.tmall.pojo.OrderItem;
import com.wsx.tmall.pojo.OrderItemExample;
import com.wsx.tmall.pojo.Product;
import com.wsx.tmall.service.OrderItemService;
import com.wsx.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frank on 2018/5/4.
 */

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductService productService;

    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id asc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem result = orderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    public void setProduct(List<OrderItem> orderItems){
        // 遍历调用单条
        for(OrderItem orderItem : orderItems){
            setProduct(orderItem);
        }
    }

    // 设置orderItem对应product属性
    private void setProduct(OrderItem orderItem){
        Product product = productService.get(orderItem.getPid());
        orderItem.setProduct(product);
    }

    @Override
    public void fill(List<Order> orders) {
        for(Order order : orders){
            fill(order);
        }
    }

    @Override
    public void fill(Order order) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo(order.getId());
        example.setOrderByClause("id asc");
        // 根据订单ID查询订单项
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        // 设置product属性用于前端显示
        setProduct(orderItems);
        float total = 0;
        int totalNumber = 0;
        // 遍历订单项，计算出总额和总数
        for(OrderItem orderItem : orderItems){
            total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
            totalNumber += orderItem.getNumber();
        }
        // 将订单项属性设置在订单上
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }
}
