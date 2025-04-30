package com.fincodehub.mail.controller;


import cn.hutool.core.util.RandomUtil;
import com.fincodehub.mail.entity.MailInfo;
import com.fincodehub.mail.service.SendMail;
import com.fincodehub.mail.util.MailTemplateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author YangJian
 * @version 1.0.0
 * @title controllerMailController
 * @create 2025/4/30 13:22
 * @description <TODO description class purpose>
 */
@Controller
public class MailController {

    private SendMail sendMail;
    private MailTemplateUtil mailTemplateUtil;
    public MailController(SendMail sendMail, MailTemplateUtil mailTemplateUtil  ) {
        this.sendMail = sendMail;
        this.mailTemplateUtil = mailTemplateUtil;
    }
    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }
    @GetMapping("/index")
    public String toIndex(Model model) {

        return "index";
    }
    @PostMapping("/submitForm")
    public String sendMail(@RequestParam("file") MultipartFile file, @ModelAttribute MailInfo mailInfo, Model model) throws IOException {
        System.out.println("mailInfo::"+mailInfo.toString());
        //不使用模版
        //boolean result = sendMail.sendEMail(mailInfo);
        //
        // EMailCmd eMailCmd = EMailCmd.builder()
        //                 .tos(List.of(to))
        //                 .subject("测试邮件")
        //                 .text(templateUtil.getCaptchaTempl(to, RandomUtil.randomNumbers(5)))
        //                 .build();
        //使用模版
        mailInfo.setContent(mailTemplateUtil.getMailTemplat("1475136846@qq.com", RandomUtil.randomNumbers(5)));
        boolean result = sendMail.sendHtmlEMail(mailInfo);
        model.addAttribute("msg", result?"发送成功":"发送失败");
        return "success";
    }

    @PostMapping("/submitForm1")
    public String sendMail1(@RequestParam("file") MultipartFile file, @ModelAttribute MailInfo mailInfo, Model model) throws IOException {
        System.out.println("mailInfo::"+mailInfo.toString());

        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String name = file.getName();
        System.out.println("contentType:" + contentType);
        System.out.println("originalFilename:" + originalFilename);
        System.out.println("name:" + name);
        //文件名不为空则上传
        if (!StringUtils.isEmpty(originalFilename)) {
            //文件存放位置
            //1.传统的方式获取服务器跟目录下的fileUpload路径
            //String realPath = request.getServletContext().getRealPath("/fileUpload");
            //2.Springboot项目中获取resource/static/fileupload路径
            String uploadPath = System.getProperty("user.dir") + "/doccenter/";
            // String uploadPath = ResourceUtils.getURL("classpath:").getPath() + "static/fileUpload/";
            System.out.println("uploadPath:" + uploadPath);
            //文件输出目录是否存在,不存在则创建
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                System.out.println("创建目录===");
                boolean mkdir = dir.mkdir();
                if (mkdir) {
                    System.out.println("创建目录成功===");
                }else{
                    System.out.println("创建目录失败===");
                }
            }
            //文件名前缀 日期+UUID
            String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + UUID.randomUUID().toString().replace("-", "");
            //文件后缀名
            String fileExtendName = "";
            int lastDotIndex = originalFilename.lastIndexOf('.');
            if (lastDotIndex > 0 && lastDotIndex < originalFilename.length() - 1) {
                fileExtendName = originalFilename.substring(lastDotIndex + 1);
            }
            //输出到指定的位置
            String outFileName = fileNamePrefix.concat(".").concat(fileExtendName);
            System.out.println("outFileName:" + outFileName);
            //文件上传至指定目录
            file.transferTo(new File(dir, outFileName));
           boolean result = sendMail.sendFileEMail(mailInfo, dir.getPath()+"\\"+outFileName);
            model.addAttribute("msg", result?"发送成功":"发送失败");
        }
        return "success";
    }

}
