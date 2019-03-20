package com.tenly.project.bean;

public class LoseCause {
	/**
	lose_id	int	20	0	0	-1	0	0	0		0	主键自增				-1	0
	cause_name	varchar	50	0	0	0	0	0	0		0	原因 名称	utf8	utf8_general_ci		0	0
	remarks	varchar	20	0	-1	0	0	0	0		0	备注	utf8	utf8_general_ci		0	0
	types	varchar	1	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
	other	varchar	20	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
	 */
	private Integer lose_id;
	private String cause_name;
	private String remarks;
	private String types;
	private String other;
	public Integer getLose_id() {
		return lose_id;
	}
	public void setLose_id(Integer lose_id) {
		this.lose_id = lose_id;
	}
	public String getCause_name() {
		return cause_name;
	}
	public void setCause_name(String cause_name) {
		this.cause_name = cause_name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public LoseCause(Integer lose_id, String cause_name, String remarks,
			String types, String other) {
		this.lose_id = lose_id;
		this.cause_name = cause_name;
		this.remarks = remarks;
		this.types = types;
		this.other = other;
	}
	public LoseCause() {
	}
}
