package com.wsx.tmall.test;

import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.pojo.Property;
import com.wsx.tmall.service.CategoryService;
import com.wsx.tmall.service.PropertyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PropertyService propertyService;

    @Test
    public void list() {
        List<Category> categories = categoryService.list();
        for(Category category : categories) {
            System.out.println(category.getId() + "\t" + category.getName());
        }
    }

    @Test
    public void listByCid(){
        int cid = 13;
        List<Property> properties = propertyService.list(cid);
        for(Property property : properties){
            System.out.println(property.getId() + "\t" + property.getName());
        }
    }

    @Test
    public void get() {
        Category category = categoryService.get(11);
        System.out.println(category.getName());
    }

    /*@Test
    public void total() {
        int count = categoryService.total();
        System.out.println(count);
    }*/

    @Test
    public void add() {
        Category category = new Category();
        category.setName("new category");
        categoryService.add(category);
    }

    @Test
    public void update() {
        Category category = new Category();
        category.setId(64);
        category.setName("update category");
        categoryService.update(category);
    }

    @Test
    public void delete() {
        int id = 65;
        categoryService.delete(id);
    }
}
