package com.suvan.mybatis.paging.model;

import java.io.Serializable;

/**
 * @描述:基model，里面会有一些基础属性，比如分页
 * 注意-----〉不要修改现有的属性，可以自己添加！！！！
 * @作者: zhangpan
 * @创建日期: 2017年1月16日 上午10:40:27
 */
@SuppressWarnings("serial")
public class BasePagingModel implements Serializable{
	
	/**
	 * 查询规则
	 */
	private QuerySchema querySchema;
	
	/** 
	 * 记录总数
	 */
	protected int total;

	/**
	 * 一页显示数目
	 * 默认：20个，可以修改
	 */
	protected Integer pagesize = 20;

	/**
	 * 总的页数
	 */
	protected Integer pagetotal = 0;

	/**
	 * 当前显示页
	 */
	protected Integer page = 0;
	
	/**
	 * 排序类型
	 */
	protected String sortType;
	
	/**
	 * 排序名称
	 */
	protected String sortName;
	
	/**
	 * jqgrid 排序的列
	 */
	protected String sidx;
	
	/**
	 * jqgrid 排序方式asc desc
	 */
	protected String sord;
	
	/**
	 * 模糊查询的列名称
	 */
	protected String likeColumnName;

	public final QuerySchema getQuerySchema() {
		return querySchema;
	}

	public final void setQuerySchema(QuerySchema querySchema) {
		this.querySchema = querySchema;
	}

	public final int getTotal() {
		return total;
	}

	public final void setTotal(int total) {
		this.total = total;
	}

	public final Integer getPagesize() {
		return pagesize;
	}

	public final void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public final Integer getPagetotal() {
		return pagetotal;
	}

	public final void setPagetotal(Integer pagetotal) {
		this.pagetotal = pagetotal;
	}

	public final Integer getPage() {
		return page;
	}

	public final void setPage(Integer page) {
		this.page = page;
	}

	public final String getSortType() {
		return sortType;
	}

	public final void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public final String getSortName() {
		return sortName;
	}

	public final void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public final String getLikeColumnName() {
		return likeColumnName;
	}

	public final void setLikeColumnName(String likeColumnName) {
		this.likeColumnName = likeColumnName;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}
}
