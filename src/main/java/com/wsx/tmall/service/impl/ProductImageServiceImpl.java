package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.ProductImageMapper;
import com.wsx.tmall.pojo.ProductImage;
import com.wsx.tmall.pojo.ProductImageExample;
import com.wsx.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frank on 2018/5/1.
 */

@Service
public class ProductImageServiceImpl implements ProductImageService{
    @Autowired
    private ProductImageMapper productImageMapper;

    @Override
    public List<ProductImage> list(int pid, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andIdEqualTo(pid).andTypeEqualTo(type);
        example.setOrderByClause("id asc");
        return productImageMapper.selectByExample(example);
    }

    @Override
    public ProductImage get(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }
}
