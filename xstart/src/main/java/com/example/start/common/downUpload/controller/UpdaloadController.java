package com.example.start.common.downUpload.controller;

import com.example.start.common.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UpdaloadController extends BaseController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String,Object> upload(HttpServletRequest request,MultipartFile file) {
        try {
            String uploadDir = FileIsExists(request);
            executeUpload(file, uploadDir);

        } catch (Exception e) {
            e.printStackTrace();
            return failure("上传失败！");
        }
        return success("上传成功！");
    }

    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public Map<String,Object> uploads(HttpServletRequest request/*,MultipartFile[] files*/) {
        try {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            String uploadDir = FileIsExists(request);
            for (MultipartFile file : files) {
                if(file!=null){
                    executeUpload(file, uploadDir);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return failure("上传失败！");
        }
        return success("上传成功！");
    }

    private String FileIsExists(HttpServletRequest request) {
        String uploadDir = "D:/upload/images";
        File dir = new File(uploadDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return uploadDir;
    }

    private void executeUpload(MultipartFile file, String uploadDir) throws IOException {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + suffix;

        File savedFile = new File(uploadDir,fileName);
        file.transferTo(savedFile);
    }

}
