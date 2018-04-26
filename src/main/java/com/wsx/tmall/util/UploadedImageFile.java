package com.wsx.tmall.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by frank on 2018/4/26.
 */
public class UploadedImageFile {
    // 上传文件注入类
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
