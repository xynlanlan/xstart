package com.example.start.common.downUpload.service.impl;

import com.example.start.common.base.Pager;
import com.example.start.common.downUpload.service.ExcelDownApi;
import com.example.start.common.downUpload.entity.FormParamsVo;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.senum.ExcelExportEnum;
import com.example.start.common.utils.ExcelExportUtil;
import com.example.start.module.entity.SysUser;
import com.example.start.module.service.SysUserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 用户导出
 */
@Service("sysUserExcleDownApiImpl")
public class SysUserExcleDownApiImpl implements ExcelDownApi {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public ExcelExportEnum getExcelExportEnum() {
        return ExcelExportEnum.USER;
    }

    @Override
    public LinkedHashMap<String, String> getTitle() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("账号", "loginAccount");
        map.put("昵称", "userName");
        map.put("性别", "sex");
        map.put("手机号", "phone");
        map.put("邮箱", "email");
        return map;
    }

    @Override
    public List<SysUser> getData(FormParamsVo form) throws ServiceException {
        Pager<SysUser> page = new Pager<>();
        page.setPageSize(Integer.MAX_VALUE);
        page.setCondition(form.getSysUser());
        return sysUserService.findByPager(page).getResult();
    }

    @Override
    public void createTeableData(String[] colContents, Object object, HSSFRow row) {
        HSSFCell cell = null;
        String colContent = "";
        SysUser user = (SysUser) object;
        for (int i = 0; i < colContents.length; i++) {
            colContent = colContents[i];
            cell = row.createCell(i+1);
            cell.setCellStyle(ExcelExportUtil.contentStyle);
            if(colContent.equals("loginAccount")){
                cell.setCellValue(user.getLoginAccount());
            }else if(colContent.equals("userName")){
                cell.setCellValue(user.getUserName());
            }else if(colContent.equals("sex")){
                cell.setCellValue(user.getSex()?"女":"男");
            }else if(colContent.equals("phone")){
                cell.setCellValue(user.getPhone());
            }else if(colContent.equals("email")){
                cell.setCellValue(user.getEmail());
            }
        }
    }

}
