package com.example.start.common.utils;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
	private static final Log logger = LogFactory.getLog(StringUtil.class);

	public static String encodeFileName(String fileName, String userAgent) {
		try {
			if (userAgent != null && fileName != null) {
				userAgent = userAgent.toLowerCase();
				if (userAgent.indexOf("firefox") != -1) {
					fileName = new String(fileName.getBytes(), "iso8859-1");
				} else if (userAgent.indexOf("msie") != -1) {
					fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
				} else {
					fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
				}
			}
			return fileName;
		} catch (Exception e) {
			return fileName;
		}
	}
	
	
	public static String listToString(List<?> list) {
		String str="";
		try {
			for (int i = 0; i < list.size(); i++) {
				str+= list.get(i)+",";
			}
			if(list.size()>0){
				str=str.substring(0,str.length()-1);
			}
		} catch (Exception e) {
			return str;
		}
		return str;
	}
}
