package com.tenly.system.bean;
// default package

/**
 * SysRoleMenu entity. @author MyEclipse Persistence Tools
 */

public class SysRoleMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String menuId;
	private String roleId;
	public SysRoleMenu() {
	}
	public SysRoleMenu(String id, String menuId, String roleId) {
		this.id = id;
		this.menuId = menuId;
		this.roleId = roleId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}