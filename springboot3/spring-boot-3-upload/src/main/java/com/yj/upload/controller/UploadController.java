package com.yj.upload.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author yangjian
 * @version 1.0.0
 * @email 2628168756@qq.com
 * @date 2024/1/19 21:59
 */
@RestController
@Slf4j
@RequestMapping("/upload")
public class UploadController {
    @Value("${spring.servlet.multipart.location}")
    private String fileTempPath;


    @PostMapping(value = "/local",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadLocal(@RequestParam("file")MultipartFile file){
        if (file == null || file.isEmpty()){
            return "文件内容为空";
        }
        // 获取文件名以及后缀名
        String fileName = file.getOriginalFilename();
        // 重新生成文件名（根据具体情况生成对应文件名）
        String rawFileName = StrUtil.subBefore(fileName, ".", true);
        String fileType = StrUtil.subAfter(fileName, ".", true);
        String localFilePath = StrUtil.appendIfMissing(fileTempPath, "/") + rawFileName + "-" + DateUtil.current() + "." + fileType;
        log.info("新的文件名：{}",localFilePath);
        try {
            file.transferTo(new File(localFilePath));
        } catch (IOException e) {
            log.error("【文件上传至本地】失败，绝对路径：{}", localFilePath);
            return "文件上传失败";
        }

        log.info("【文件上传至本地】绝对路径：{}", localFilePath);
        return "上传成功";
    }
}
