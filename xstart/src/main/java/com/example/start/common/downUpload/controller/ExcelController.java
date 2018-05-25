package com.example.start.common.downUpload.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.start.common.base.BaseController;
import com.example.start.common.downUpload.service.ExcelDownApi;
import com.example.start.common.utils.ExcelExportUtil;
import com.example.start.module.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@SuppressWarnings("all")
@RestController()
@RequestMapping("api")
public class ExcelController extends BaseController {

    @Autowired
    @Qualifier("sysUserExcleDownApiImpl")
    private ExcelDownApi sysUserExcleDownApiImpl;

    @RequestMapping(value = "download/{name}")
    public void getSystemExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name) {
        try {
            String str = request.getParameter("downParams");
            if(str !=null){
                str = URLDecoder.decode(str,"UTF-8");
            }
            if("user".equals(name)){
                SysUser sysUser = JSONObject.parseObject(str, SysUser.class);
                ExcelExportUtil.downloadExcel(request,response,sysUserExcleDownApiImpl,sysUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
