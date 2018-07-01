package com.suvan.mybatis.paging.model;

import com.suvan.mybatis.util.EmptyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 查询规范
 */
public class QuerySchema {
	
	/**
	 * 基础model的类型
	 */
	private Class<?> modelClaz;
	
	/**
	 * 等于，列无视类型
	 */
	private List<QueryField> eqQueryFields;
	
	/**
	 * in查询，需要指定每个列的数据类型
	 */
	private List<QueryField> inQueryFields;
	
	/**
	 * 模糊查询，针对字符串类型
	 */
	private List<QueryField> likeQueryFields;
	
	/**
	 * 范围查询，需要指定数据类型
	 */
	private List<QueryField> rangeQueryFields;
	
	public QuerySchema(){}
	
	public QuerySchema(Class<?> modelClaz, String[] eqColumnNames, String[] inColumnNames, 
			String[] likeColumnNames, String[] rangeColumnNames){
		this.modelClaz = modelClaz;
		
		this.eqQueryFields = new ArrayList<QueryField>();
		initQueryField(eqColumnNames, this.eqQueryFields);
		
		this.inQueryFields = new ArrayList<QueryField>();
		initQueryField(inColumnNames, this.inQueryFields);
		
		this.likeQueryFields = new ArrayList<QueryField>();
		initQueryField(likeColumnNames, this.likeQueryFields);
		
		this.rangeQueryFields = new ArrayList<QueryField>();
		initQueryField(rangeColumnNames, this.rangeQueryFields);
	}
	
	public QuerySchema(List<QueryField> eqQueryFields, List<QueryField> inQueryFields,
                       List<QueryField> likeQueryFields, List<QueryField> rangeQueryFields){
		this.eqQueryFields = eqQueryFields;
		this.inQueryFields = inQueryFields;
		this.likeQueryFields = likeQueryFields;
		this.rangeQueryFields = rangeQueryFields;
	}
	
	public QuerySchema(QueryField[] eqQueryFields, QueryField[] inQueryFields,
                       QueryField[] likeQueryFields, QueryField[] rangeQueryFields){
		this.eqQueryFields = EmptyUtil.isEmpty(eqQueryFields) ? null : Arrays.asList(eqQueryFields);
		this.inQueryFields = EmptyUtil.isEmpty(inQueryFields) ? null : Arrays.asList(inQueryFields);
		this.likeQueryFields = EmptyUtil.isEmpty(likeQueryFields) ? null : Arrays.asList(likeQueryFields);
		this.rangeQueryFields = EmptyUtil.isEmpty(rangeQueryFields) ? null : Arrays.asList(rangeQueryFields);
	}
	
	/**
	 * 初始化查询域的类型
	 * @param columnNames
	 * @param queryFields
	 * @throws Exception
	 */
	public void initQueryField(String[] columnNames, List<QueryField> queryFields){
		if(EmptyUtil.isEmpty(columnNames) || this.modelClaz == null){
			return;
		}
		
		for(String columnName : columnNames){
			try {
				queryFields.add(new QueryField(columnName, this.modelClaz.getDeclaredField(columnName).getType()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<QueryField> getEqColumnNames() {
		return eqQueryFields;
	}

	public void setEqColumnNames(List<QueryField> eqColumnNames) {
		this.eqQueryFields = eqColumnNames;
	}

	public List<QueryField> getInColumnNames() {
		return inQueryFields;
	}

	public void setInColumnNames(List<QueryField> inColumnNames) {
		this.inQueryFields = inColumnNames;
	}

	public List<QueryField> getLikeColumnNames() {
		return likeQueryFields;
	}

	public void setLikeColumnNames(List<QueryField> likeColumnNames) {
		this.likeQueryFields = likeColumnNames;
	}

	public List<QueryField> getRangeColumnNames() {
		return rangeQueryFields;
	}

	public void setRangeColumnNames(List<QueryField> rangeColumnNames) {
		this.rangeQueryFields = rangeColumnNames;
	}
}
