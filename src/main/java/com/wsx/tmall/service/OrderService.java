package com.wsx.tmall.service;

import com.wsx.tmall.pojo.Order;

import java.util.List;

/**
 * Created by frank on 2018/5/4.
 */

public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    List<Order> list();

    Order get(int id);

    void update(Order order);

    void add(Order order);

    void delete(int id);
}
