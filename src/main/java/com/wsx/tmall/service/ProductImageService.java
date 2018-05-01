package com.wsx.tmall.service;

import com.wsx.tmall.pojo.ProductImage;

import java.util.List;

/**
 * Created by frank on 2018/5/1.
 */
public interface ProductImageService {

    String type_single = "type_single";
    String type_detail = "type_detail";

    List<ProductImage> list(int pid, String type);

    ProductImage get(int id);

    void update(ProductImage productImage);

    void add(ProductImage productImage);

    void delete(int id);
}
