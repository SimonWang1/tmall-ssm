package com.wsx.tmall.service;

import com.wsx.tmall.pojo.Product;
import com.wsx.tmall.pojo.PropertyValue;

import java.util.List;

/**
 * Created by frank on 2018/5/3.
 */
public interface PropertyValueService {
    void init(Product product);

    List<PropertyValue> list(int pid);

    PropertyValue get(int pid, int ptid);

    void update(PropertyValue propertyValue);
}
