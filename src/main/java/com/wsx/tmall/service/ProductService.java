package com.wsx.tmall.service;

import com.wsx.tmall.pojo.Product;

import java.util.List;

/**
 * Created by frank on 2018/4/28.
 */
public interface ProductService {
    List<Product> list(int cid);

    Product get(int id);

    void update(Product product);

    void add(Product product);

    void delete(int id);
}
