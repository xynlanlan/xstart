package com.example.start.common.downUpload.controller;

import com.example.start.common.base.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UpdaloadController extends BaseController {

    @Value("${web.upload.path}")
    private String fileUploadPath;

    @Value("${web.upload.jsoup}")
    private String webUploadUrl;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String,Object> upload(HttpServletRequest request,MultipartFile file) {
        String fileUrl = null;
        try {
            String uploadDir = FileIsExists(request);
            fileUrl = executeUpload(file, uploadDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success(fileUrl);
    }

    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public Map<String,Object> uploads(HttpServletRequest request/*,MultipartFile[] files*/) {
        List<String> fileUrls = new ArrayList<>();
        try {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            String uploadDir = FileIsExists(request);
            for (MultipartFile file : files) {
                if(file!=null){
                    String fileUrl = executeUpload(file, uploadDir);
                    fileUrls.add(fileUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success(fileUrls);
    }

    private String FileIsExists(HttpServletRequest request) throws FileNotFoundException {
        File dir = new File(fileUploadPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    private String executeUpload(MultipartFile file, String uploadDir) throws IOException {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + suffix;

        File savedFile = new File(uploadDir,fileName);
        file.transferTo(savedFile);
        return webUploadUrl + fileName;
    }

}
