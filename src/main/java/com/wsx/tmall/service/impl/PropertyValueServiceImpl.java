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
    private PropertyService propertyService;
    @Autowired
    private PropertyValueMapper propertyValueMapper;

    @Override
    public void init(Product product) {
        // 通过product获取cid查询property用于动态生成propertyValue名称
        List<Property> properties = propertyService.list(product.getCid());
        // 遍历property
        for(Property property : properties){
            // 通过product和property id查询对应属性值
            PropertyValue propertyValue = get(product.getId(), property.getId());
            // 若为空进行初始化，依次实例化propertyValue并插入对应product和property id，propertyValue为null
            if(propertyValue == null){
                propertyValue = new PropertyValue();
                propertyValue.setPid(product.getId());
                propertyValue.setPtid(property.getId());
                propertyValueMapper.insert(propertyValue);
            }
        }
    }

    @Override
    public List<PropertyValue> list(int pid) {
        // 通过产品pid获取propertyValue
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        // 遍历propertyValue
        for(PropertyValue propertyValue : pvs){
            // 通过属性ptid依次获取property用于前端动态显示propertyValue对应property名称
            Property property = propertyService.get(propertyValue.getPtid());
            // 赋值操作
            propertyValue.setProperty(property);
        }
        return pvs;
    }

    @Override
    // 通过产品pid和属性ptid获取单条数据用于编辑
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
