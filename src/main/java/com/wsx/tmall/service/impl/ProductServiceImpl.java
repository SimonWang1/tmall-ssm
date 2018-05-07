package com.wsx.tmall.service.impl;

import com.wsx.tmall.mapper.ProductMapper;
import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.pojo.Product;
import com.wsx.tmall.pojo.ProductExample;
import com.wsx.tmall.pojo.ProductImage;
import com.wsx.tmall.service.CategoryService;
import com.wsx.tmall.service.ProductImageService;
import com.wsx.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private ProductImageService productImageService;

    @Override
    public List<Product> list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id asc");
        List result = productMapper.selectByExample(example);
        // 每条数据获取对应category用于前端显示
        setCategory(result);
        // 每条数据对应图片
        setFirstProductImage(result);
        return result;
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        // 获取对应category用于前端显示
        setCategory(product);
        // 获取图片
        setFirstProductImage(product);
        return product;
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

    public void setCategory(List<Product> products){
        for(Product product : products){
            // 遍历调用单条
            setCategory(product);
        }
    }

    // 设置product对应category属性
    public void setCategory(Product product){
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
    }

    public void setFirstProductImage(List<Product> products){
        for(Product product : products){
            // 遍历调用单条
            setFirstProductImage(product);
        }
    }

    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> pis = productImageService.list(product.getId(), productImageService.type_single);
        if(!pis.isEmpty()){
            // 取查询的第一条图片当做产品略缩图
            ProductImage productImage = pis.get(0);
            product.setFirstProductImage(productImage);
        }
    }

    @Override
    // 为categories填充product属性
    public void fill(List<Category> categories) {
        // 遍历调用单条
        for(Category category : categories){
            fill(category);
        }
    }

    @Override
    // 为category填充product属性
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        category.setProducts(products);
    }

    @Override
    // 为categories填充8个为一行的推荐product集合
    public void fillByRow(List<Category> categories) {
        int productNumberEachRow = 8;
        for(Category category : categories){
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for(int i = 0; i < products.size(); i += productNumberEachRow){
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productOfEachRow = products.subList(i, size);
                productsByRow.add(productOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }
}
