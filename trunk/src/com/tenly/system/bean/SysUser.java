package com.tenly.system.bean;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
public class SysUser implements java.io.Serializable {
	// Fields
	private Integer id;
	private String realname;
	private String username;
	private String portrait;
	private String password;
	private String salt;
	private String email;
	private String phone;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private String remarks;
	private String delFlag;
	private Set<SysRole> roles = new HashSet(0);
	private List<SysOrganization> organizations;
	
	private String jiwuduan_code;
	
	
	public String getJiwuduan_code() {
		return jiwuduan_code;
	}

	public void setJiwuduan_code(String jiwuduan_code) {
		this.jiwuduan_code = jiwuduan_code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

	public List<SysOrganization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<SysOrganization> organizations) {
		this.organizations = organizations;
	}

	public SysUser(Integer id, String realname, String username,
			String portrait, String password, String salt, String email,
			String phone, String createBy, Timestamp createDate,
			String updateBy, Timestamp updateDate, String remarks,
			String delFlag, Set<SysRole> roles,
			List<SysOrganization> organizations, String jiwuduan_code) {
		this.id = id;
		this.realname = realname;
		this.username = username;
		this.portrait = portrait;
		this.password = password;
		this.salt = salt;
		this.email = email;
		this.phone = phone;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.remarks = remarks;
		this.delFlag = delFlag;
		this.roles = roles;
		this.organizations = organizations;
		this.jiwuduan_code = jiwuduan_code;
	}

	public SysUser() {
	}
}