package com.example.start.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.start.common.downUpload.service.ExcelDownApi;
import com.example.start.common.entity.BaseEntity;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.senum.ExcelExportEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class ExcelExportUtil {

	protected static Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);
	
	private static CellStyle titleStyle; // 标题行样式
	private static Font titleFont; // 标题行字体
	private static CellStyle headStyle; // 表头行样式
	private static Font headFont; // 表头行字体
	public static CellStyle contentStyle; // 内容行样式
	private static Font contentFont; // 内容行字体

	private static ExcelDownApi api;
	private static ExcelExportEnum excelExportEnum;/** excel文件名称，工作簿和生成的内容标题都用这个名称 */
	private static LinkedHashMap<String, String> map = null;/** 列名与集合中实体类属性的对应关系*/
	private static List<?> list = null;/** 需要导出的数据集合 */

	/**
	 * 初始化HSSFWorkbook
	 * 
	 * @Method_Name : init
	 * @return  HSSFWorkbook
	 */
	private static HSSFWorkbook init(ExcelDownApi excelDownApi, BaseEntity form) throws ServiceException {
		HSSFWorkbook wb = new HSSFWorkbook();

		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();
		api = excelDownApi;
		excelExportEnum = api.getExcelExportEnum();
		map = api.getTitle();
		list = api.getData(form);

		initTitleCellStyle();
		initTitleFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
		return wb;
	}

	/**
	 * 根据数据集合生成excel文件并下载
	 * @param request
	 * @param response
	 * @param excelDownApi 下载实现接口
	 * @param form  表单参数
	 * @throws Exception
	 */
	public static void downloadExcel(HttpServletRequest request, HttpServletResponse response, ExcelDownApi excelDownApi, BaseEntity form /*FormParamsVo form*/) throws Exception {

		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFWorkbook wb = init(excelDownApi,form);
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet(excelExportEnum.getTypeName());  
        
        int columnNum = map.keySet().size();
        String[] colNames = (String[]) map.keySet().toArray(new String[columnNum]);
		String[] colContents = (String[]) map.values().toArray(new String[columnNum]);
		
		// 第三步，填充sheet 数据
		createTableTitleRow(sheet, excelExportEnum.getTypeName(), columnNum);
        creatTableHeadRow(sheet.createRow((int) 1), colNames);
        creatTableDataRows(sheet, colContents);
        
        // 第四步，自动调整列宽
        adjustColumnSize(sheet, columnNum);
        
        // 第六步，输出Excel文件  
        String fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "_" + excelExportEnum.getTypeName() +".xls";
	    outWrite(request, response, wb, fileName);  
	}

	private static void outWrite(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook wb,
			String fileName) throws IOException {
		OutputStream output = null;
		try {
			String userAgent = request.getHeader("User-Agent");
			output = response.getOutputStream();
			response.reset();  
			response.setHeader("Content-disposition", "attachment; filename="+ StringUtil.encodeFileName(fileName, userAgent));  
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setCharacterEncoding("utf-8");  
			wb.write(output);  
			output.flush(); 
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(output != null){
				output.close();  
			}
		}
	}
	
	/**
	 * @Description: 创建标题行(需合并单元格)
	 */
	private static void createTableTitleRow(HSSFSheet sheet, String fileName, int columnNum) {
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 1, columnNum);
		sheet.addMergedRegion(titleRange);
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 800);
		HSSFCell cell = row.createCell(1);
		cell.setCellStyle(titleStyle);
		cell.setCellValue(fileName);
	}
	
	/**
	 * @Description: 创建表头行(需合并单元格)
	 */
	private static void creatTableHeadRow(HSSFRow row, String[] colNames) {
		// 表头
		row.setHeight((short) 400);
		// 列头名称
		HSSFCell cell = null;
		for (int i = 0; i < colNames.length; i++) {
			cell = row.createCell(i + 1);
			cell.setCellStyle(headStyle);
			cell.setCellValue(colNames[i]);
		}
	}
	
	/**
	 * @Description: 创建表格数据
	 */
	private static void creatTableDataRows(HSSFSheet sheet, String[] colContents) {
		HSSFRow row = null;
		for (int i = 0; i < list.size(); i++) { 
        	row = sheet.createRow((int) i + 2);
    		row.setHeight((short) 350);
			api.createTeableData(colContents, list.get(i), row);
        }
	}

	/**
	 * @Description: 自动调整列宽
	 */
	private static void adjustColumnSize(HSSFSheet sheet, int columnNum) {
		for (int i = 0; i < columnNum; i++) {
			sheet.autoSizeColumn(i + 1);
		}
	}
	
	/**
	 * @Description: 初始化标题行样式
	 */
	private static void initTitleCellStyle() {
		titleStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	}
	
	/**
	 * @Description: 初始化表头行样式
	 */
	private static void initHeadCellStyle() {
		headStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		headStyle.setBorderTop(BorderStyle.MEDIUM);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		headStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	}
	
	/**
	 * @Description: 初始化内容行样式
	 */
	private static void initContentCellStyle() {
		contentStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop(BorderStyle.THIN);
		contentStyle.setBorderBottom(BorderStyle.THIN);
		contentStyle.setBorderLeft(BorderStyle.THIN);
		contentStyle.setBorderRight(BorderStyle.THIN);
		contentStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		contentStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		contentStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		contentStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		contentStyle.setWrapText(true); // 字段换行
	}
	
	/**
	 * @Description: 初始化标题行字体
	 */
	private static void initTitleFont() {
		titleFont.setFontName("华文楷体");
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBold(true);
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLACK.getIndex());
	}
	
	/**
	 * @Description: 初始化表头行字体
	 */
	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBold(true);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.BLACK.getIndex());
	}

	/**
	 * @Description: 初始化内容行字体
	 */
	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBold(false);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLACK.getIndex());
	}
}
