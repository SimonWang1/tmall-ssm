package com.wsx.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsx.tmall.pojo.Category;
import com.wsx.tmall.service.CategoryService;
import com.wsx.tmall.util.ImageUtil;
import com.wsx.tmall.util.Page;
import com.wsx.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by frank on 2018/4/24.
 */

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Page page, Model model) {
        // 设置分页参数
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Category> categories = categoryService.list();
        // 获取总数
        int total = (int) new PageInfo<>(categories).getTotal();
        page.setTotal(total);
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_edit")
    public String get(int id, Model model) throws IOException{
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException{
        categoryService.update(category);
        // 获取当前页面上传图片
        MultipartFile image = uploadedImageFile.getImage();
        // 判断是否需要更新图片
        if(image != null && !image.isEmpty()) {
            // 获取路径
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
            // 通过ID创建文件名
            File file = new File(imageFolder, category.getId() + ".jpg");
            // 保存图片
            image.transferTo(file);
            // 将文件转换为jpg格式
            BufferedImage img = ImageUtil.change2jpg(file);
            // 上传
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:admin_category_list";
    }

    @RequestMapping("admin_category_add")
    public String add(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException{
        categoryService.add(category);
        // 定位上传文件保存位置
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        // 根据ID创建文件名
        File file = new File(imageFolder, category.getId() + ".jpg");
        // 创建路径
        if(!file.getParentFile().exists())
            file.getParentFile().mkdir();
        // 保存图片
        uploadedImageFile.getImage().transferTo(file);
        // 将文件转换为jpg格式
        BufferedImage image = ImageUtil.change2jpg(file);
        // 上传
        ImageIO.write(image, "jpg", file);
        return "redirect:admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id, HttpSession session) throws IOException{
        categoryService.delete(id);
        // 删除图片
        File imageFolder = new File(session.getServletContext().getRealPath("image/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();
        return "redirect:admin_category_list";
    }
}
