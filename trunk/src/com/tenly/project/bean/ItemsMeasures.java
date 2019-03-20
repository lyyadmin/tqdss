package com.tenly.project.bean;

public class ItemsMeasures {
	private Integer item_id;
	private String item_name;
	private String item_measures;
	private String item_type;
	private String item_other;
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_measures() {
		return item_measures;
	}
	public void setItem_measures(String item_measures) {
		this.item_measures = item_measures;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_other() {
		return item_other;
	}
	public void setItem_other(String item_other) {
		this.item_other = item_other;
	}
	public ItemsMeasures(Integer item_id, String item_name,
			String item_measures, String item_type, String item_other) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_measures = item_measures;
		this.item_type = item_type;
		this.item_other = item_other;
	}
	public ItemsMeasures() {
	}
	
}	
