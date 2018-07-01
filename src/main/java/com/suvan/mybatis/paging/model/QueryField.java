package com.suvan.mybatis.paging.model;

/**
 * 查询字段格式说明
 */
public class QueryField{
	
	private String fieldName;
	
	private Class<?> fieldType;
	
	public QueryField(String fieldName, Class<?> fieldType){
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Class<?> getFieldType() {
		return fieldType;
	}
	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}
}
