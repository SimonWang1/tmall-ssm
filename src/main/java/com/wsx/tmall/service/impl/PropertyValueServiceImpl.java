package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.PropertyValueMapper;
import com.wsx.tmall.pojo.Product;
import com.wsx.tmall.pojo.Property;
import com.wsx.tmall.pojo.PropertyValue;
import com.wsx.tmall.pojo.PropertyValueExample;
import com.wsx.tmall.service.PropertyService;
import com.wsx.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frank on 2018/5/3.
 */

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    private PropertyValueMapper propertyValueMapper;

    @Autowired
    private PropertyService propertyService;

    @Override
    public void init(Product product) {
        // 根据产品获取分类，通过分类查询属性
        List<Property> properties = propertyService.list(product.getCid());
        for(Property property : properties){
            // 查询属性值
            PropertyValue propertyValue = get(product.getId(), property.getId());
            // 若为空，实例化属性值并插入到数据库
            if(propertyValue == null){
                propertyValue = new PropertyValue();
                propertyValue.setPid(product.getId());
                propertyValue.setPtid(property.getId());
                propertyValueMapper.insert(propertyValue);
            }
        }
    }

    @Override
    // 获取属性值
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        for(PropertyValue propertyValue : pvs){
            Property property = propertyService.get(propertyValue.getPtid());
            propertyValue.setProperty(property);
        }
        return pvs;
    }

    @Override
    public PropertyValue get(int pid, int ptid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid).andPtidEqualTo(ptid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if(pvs.isEmpty())
            return null;
        return pvs.get(0);
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }
}
