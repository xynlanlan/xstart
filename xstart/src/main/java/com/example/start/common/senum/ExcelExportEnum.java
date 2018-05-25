package com.example.start.common.senum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 下载类型枚举
 */
public enum ExcelExportEnum implements Serializable{
	
	USER(1, "用户列表");
 
	private Integer type;
	private String typeName;

	private ExcelExportEnum(int type, String typeName) {
		this.type = type;
		this.typeName = typeName;
	}
	
	static Map<Integer, ExcelExportEnum> enumMap = new HashMap<Integer, ExcelExportEnum>();
	static {
		for (ExcelExportEnum e : ExcelExportEnum.values()) {
			enumMap.put(e.getType(), e);
		}
	}

	public static ExcelExportEnum getEnum(Integer value) {
		return enumMap.get(value);
	}

	public Integer getType() {
		return type;
	}

	public String getTypeName() {
		return typeName;
	}

}
