package com.fincodehub.upload.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author YangJian
 * @version 1.0.0
 * @title FileController
 * @create 2025/5/16 16:12
 * @description <TODO description class purpose>
 */
@Controller
public class FileController {

    @Value("${file.upload.dir}")
    private String realPath;

    // 打开文件上传页面
    @GetMapping("/upload/page")
    public String uploadPage() {
        return "upload"; // 对应 templates/upload.html
    }

    // === 使用 HttpServletRequest ===
    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String, Object> uploadRequest(HttpServletRequest request) {
        // 强制转换为 MultipartHttpServletRequest（需Spring支持）
        if (request instanceof MultipartHttpServletRequest mreq) {
            MultipartFile file = mreq.getFile("file");
            if (file != null && !file.isEmpty()) {
                try {
                    String originalFilename = file.getOriginalFilename();
                    System.out.println("originalFilename:::" + originalFilename);

                    //文件名不为空则上传
                    if (!StringUtils.isEmpty(originalFilename)) {
                        //文件存放位置
                        //1.传统的方式获取服务器跟目录下的fileUpload路径
                        //String realPath = request.getServletContext().getRealPath("/fileUpload");
                        //2.Springboot项目中获取resource/static/fileupload路径
                        String uploadPath = ResourceUtils.getURL("classpath:").getPath() + "uploads/";
                        System.out.println("uploadPath:" + uploadPath);
                        //文件输出目录是否存在,不存在则创建
                        File dir = new File(uploadPath);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        //文件名前缀 日期+UUID
                        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + UUID.randomUUID().toString().replace("-", "");
                        //文件后缀名
                        String fileExtendName = getExtension(originalFilename);
                        //输出到指定的位置
                        String outFileName = fileNamePrefix.concat(".").concat(fileExtendName);
                        //文件上传至指定目录 使用此方法保存必须要绝对路径且文件夹必须已存在,否则报错
                        file.transferTo(new File(dir, outFileName));

                    }

                    return dealResultMap(true, "上传成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dealResultMap(false, "上传失败");
    }

    private String getExtension(String originalFilename) {
        // Snipaste_2025-03-19_07-47-18.png  获取后缀名
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }


    // === 使用 MultipartFile（单文件）===
    @PostMapping("/upload/multipart")
    @ResponseBody
    // 定义：接收文件对象 MultipartFile file变量名要与form表单中input type="file" 标签name属性名一致
    public Map<String, Object> uploadMultipartFile(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                System.out.println("文件名:::" + originalFilename);
                System.out.println("文件大小:::" + file.getSize());
                System.out.println("文件类型:::" + file.getContentType());

                // 修改文件名
                String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName = new SimpleDateFormat("yyyy-MM-dd HH-ss-SSS-").format(new Date())+UUID.randomUUID() + ext;
                // 上传文件到哪
                file.transferTo(new File(realPath, newFileName));

                return dealResultMap(true, "上传成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dealResultMap(false, "上传失败");
    }

    // === 使用 Part（单文件）===
    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadPart(@RequestPart("file") Part filePart) {
        System.out.println("文件大小:" + filePart.getSize());
        if (filePart != null && filePart.getSize() > 0) {
            try {
                Path path = Paths.get(realPath);
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                // 生成唯一文件名
                String originalFilename = filePart.getSubmittedFileName();
                String newFileName = new SimpleDateFormat("yyyy-MM-dd HH-ss-SSS-").format(new Date())+UUID.randomUUID()
                        + originalFilename.substring(originalFilename.lastIndexOf("."));

                // 保存文件
                Path filePath = path.resolve(newFileName);
                try(InputStream inputStream = filePart.getInputStream()){
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                // filePart.write(filePart.getSubmittedFileName());

                System.out.println("文件名:" + originalFilename);
                System.out.println("保存路径:" + filePath);
                System.out.println("文件类型:" + filePart.getContentType());

                return dealResultMap(true, "上传成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dealResultMap(false, "上传失败");
    }

    // === 多文件上传（MultipartFile）===
    @PostMapping("/upload/multi/multipart")
    @ResponseBody
    public Map<String, Object> uploadMultiFiles(@RequestParam("files") MultipartFile[] files) {
        boolean allSuccess = true;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String originalFilename = file.getOriginalFilename();
                    System.out.println("文件名:::" + originalFilename);
                    System.out.println("文件大小:::" + file.getSize());
                    System.out.println("文件类型:::" + file.getContentType());

                    // 修改文件名
                    String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                    String newFileName = new SimpleDateFormat("yyyy-MM-dd HH-ss-SSS-").format(new Date())+UUID.randomUUID() + ext;
                    // 上传文件到哪
                    file.transferTo(new File(realPath, newFileName));

                } catch (IOException e) {
                    e.printStackTrace();
                    allSuccess = false;
                }
            }
        }
        return dealResultMap(allSuccess, allSuccess ? "所有文件上传成功" : "部分或全部文件上传失败");
    }

    // === 辅助方法：返回结果 ===
    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }
}