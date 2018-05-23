package com.example.start.module.controller;

import com.example.start.common.base.BaseController;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
public class UpdaloadController extends BaseController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String,Object> upload(HttpServletRequest request,MultipartFile file) {
        try {
            String uploadDir = request.getSession().getServletContext().getRealPath("/upload/");
            File dir = new File(uploadDir);
            if(!dir.exists()){
                dir.mkdir();
            }
            executeUpload(file, uploadDir);

        } catch (Exception e) {
            e.printStackTrace();
            return failure("上传失败！");
        }
        return success("上传成功！");
    }

    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public Map<String,Object> uploads(HttpServletRequest request,MultipartFile[] files) {
        try {
            String uploadDir = request.getSession().getServletContext().getRealPath("/upload/");
            File dir = new File(uploadDir);
            if(!dir.exists()){
                dir.mkdir();
            }
            for (int i = 0; i < files.length; i++) {
                if(files[i]!=null){
                    executeUpload(files[i], uploadDir);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return failure("上传失败！");
        }
        return success("上传成功！");
    }

    private void executeUpload(MultipartFile file, String uploadDir) throws IOException {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + suffix;
        File serverFile = new File(uploadDir+fileName);
        file.transferTo(serverFile);
    }


}
