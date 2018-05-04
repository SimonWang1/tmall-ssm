package com.wsx.tmall.service;

import com.wsx.tmall.pojo.Order;
import com.wsx.tmall.pojo.OrderItem;

import java.util.List;

/**
 * Created by frank on 2018/5/4.
 */

public interface OrderItemService {
    List<OrderItem> list();

    OrderItem get(int id);

    void update(OrderItem orderItem);

    void add(OrderItem orderItem);

    void delete(int id);

    void fill(List<Order> orders);

    void fill(Order order);
}
