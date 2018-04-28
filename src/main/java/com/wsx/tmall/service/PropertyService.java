package com.wsx.tmall.service;

import com.wsx.tmall.pojo.Property;

import java.util.List;

/**
 * Created by frank on 2018/4/28.
 */
public interface PropertyService {
    List<Property> list(int cid);

    Property get(int id);

    void add(Property property);

    void update(Property property);

    void delete(int id);
}
