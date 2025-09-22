package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
   public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传:{}",image.getOriginalFilename());
        String url=aliOSSUtils.upload(image);
        return Result.success(url);

   }
}
/* @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传:{},{},{}",name,age,image);
        String originalFilename=image.getOriginalFilename();
        int index=originalFilename.lastIndexOf(".");
        String extname=originalFilename.substring(index);
        String newFileName= UUID.randomUUID().toString()+extname;
        image.transferTo(new File("D:\\image\\"+newFileName));
        return Result.success();
    }*/