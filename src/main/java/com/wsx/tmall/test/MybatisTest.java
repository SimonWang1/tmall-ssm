package com.wsx.tmall.test;

import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.service.CategoryService;
import com.wsx.tmall.util.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by frank on 2018/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void list() {
        Page page = new Page(0, 100);
        List<Category> categories = categoryService.list(page);
        for(Category category : categories) {
            System.out.println(category.getId() + "\t" + category.getName());
        }
    }

   /* @Test
    public void get() {
        Category category = categoryService.get(1);
        System.out.println(category.getName());
    }

    @Test
    public void count() {
        int count = categoryService.count();
        System.out.println(count);
    }*/

    @Test
    public void add() {
        Category category = new Category();
        category.setName("new category");
        categoryService.add(category);
    }

    /*@Test
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
    }*/
}
