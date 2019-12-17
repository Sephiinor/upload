package com.wx.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>Title:UploadController.java</p >
 * <p>Description:</p >
 *
 * @author Sephinor
 * @time 2019/12/16 19:24
 */
@Controller
public class UploadController {

    private static String UPLOADED_FOLDER = "D://java//";

    @GetMapping("/")
    public String index(){
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file")MultipartFile file,
                                   RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select a fle to upload");
            return "redirect:uploadStatus";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            try {
                Files.write(path,bytes);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message","文件写入失败,请查看写入文件夹权限");
            }

            redirectAttributes.addFlashAttribute("message","已成功上传'"+file.getOriginalFilename()+"'");
            System.out.println("文件上传成功");

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","文件写入失败");
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(){return "uploadStatus";}
}
