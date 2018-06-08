package com.example.start.common.downUpload.service;

import com.example.start.common.entity.BaseEntity;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.senum.ExcelExportEnum;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * excel下载接口
 */
public interface ExcelDownApi {
    ExcelExportEnum getExcelExportEnum();

    LinkedHashMap<String, String> getTitle();

    List<?> getData(BaseEntity form) throws ServiceException;

    void createTeableData(String[] colContents, Object object, HSSFRow row) ;
}
