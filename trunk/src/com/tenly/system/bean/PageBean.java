package com.tenly.system.bean;

import java.util.List;

public class PageBean {
	private int currentPage;//当前页码
	private int pageSize;//每页显示的记录数
	private int total;//总记录数
	private List rows;//当前页需要展示的数据集合
	private int records;
	private Object queryClass;//查詢條件
	public PageBean(int currentPage, int pageSize, int total, List rows,
					int records, Object queryClass) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
		this.records = records;
		this.queryClass = queryClass;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public Object getQueryClass() {
		return queryClass;
	}
	public void setQueryClass(Object queryClass) {
		this.queryClass = queryClass;
	}
	public PageBean() {
	}
	
}
