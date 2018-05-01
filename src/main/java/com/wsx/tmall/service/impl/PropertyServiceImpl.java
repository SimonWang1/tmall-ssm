package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.PropertyMapper;
import com.wsx.tmall.pojo.Property;
import com.wsx.tmall.pojo.PropertyExample;
import com.wsx.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frank on 2018/4/28.
 */

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> list(int cid) {
        PropertyExample example = new PropertyExample();
        // 关联外键
        example.createCriteria().andCidEqualTo(cid);
        // 添加条件
        example.setOrderByClause("id asc");
        return propertyMapper.selectByExample(example);
    }

    @Override
    public Property get(int id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKeySelective(property);
    }

    @Override
    public void add(Property property) {
        propertyMapper.insert(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);
    }
}
