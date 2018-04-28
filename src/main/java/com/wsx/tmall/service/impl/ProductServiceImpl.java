package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.ProductMapper;
import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.pojo.Product;
import com.wsx.tmall.pojo.ProductExample;
import com.wsx.tmall.service.CategoryService;
import com.wsx.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by frank on 2018/4/28.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id asc");
        List result = productMapper.selectByExample(example);
        // 结果集添加对应category
        setCategory(result);
        return result;
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        // 添加对应category
        setCategory(product);
        return product;
    }

    // 遍历products对应的category
    public void setCategory(List<Product> products){
        for(Product product : products){
            // 调用单条
            setCategory(product);
        }
    }

    // 单条product对应的category
    public void setCategory(Product product){
        int cid = product.getCid();
        Category category = categoryService.get(cid);
        product.setCategory(category);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }
}
