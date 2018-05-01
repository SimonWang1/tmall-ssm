package com.wsx.tmall.controller;

import com.wsx.tmall.pojo.Product;
import com.wsx.tmall.pojo.ProductImage;
import com.wsx.tmall.service.ProductImageService;
import com.wsx.tmall.service.ProductService;
import com.wsx.tmall.util.ImageUtil;
import com.wsx.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by frank on 2018/5/1.
 */

@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @RequestMapping("admin_productImage_list")
    public String list(int pid, Model model) {
        Product product = productService.get(pid);
        List<ProductImage> pisSingle = productImageService.list(pid, productImageService.type_single);
        List<ProductImage> pisDetail = productImageService.list(pid, productImageService.type_detail);
        model.addAttribute("product", product);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);
        return "admin/listProductImage";
    }

    @RequestMapping("admin_productImage_add")
    public String add(ProductImage productImage, HttpSession session, UploadedImageFile uploadedImageFile) {
        // 获取图片参数并添加
        productImageService.add(productImage);
        // 图片名称
        String fileName = productImage.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        // 如果是略缩图，声明三种路径
        if (productImageService.type_single.equals(productImage.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
        } else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
        }
        // 创建路径
        File file = new File(imageFolder, fileName);
        file.getParentFile().mkdirs();
        try {
            // 保存图片
            uploadedImageFile.getImage().transferTo(file);
            // 将文件转换为jpg格式
            BufferedImage img = ImageUtil.change2jpg(file);
            // 上传
            ImageIO.write(img, "jpg", file);
            // 转换三种略缩图大小并保存在对应路径
            if (ProductImageService.type_single.equals(productImage.getType())) {
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);
                ImageUtil.resizeImage(file, 56, 56, f_small);
                ImageUtil.resizeImage(file, 217, 190, f_middle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?pid=" + productImage.getPid();
    }
}
