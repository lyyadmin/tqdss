package com.tenly.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tenly.system.bean.SysOrganization;
import com.tenly.system.bean.SysUser;
import com.tenly.system.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbctemplate;

	public List<?> queryUserByName(SysUser user) {
//		String sql = " SELECT * FROM sys_user WHERE sys_user_name = ? ";
		String sql = " SELECT * FROM sys_user WHERE username = ? ";
		List<?> list = jdbctemplate.queryForList(sql,new Object[]{user.getUsername()});
		return list;
	}
	
	public List<?> queryUserByMd5Pwd(SysUser user) {
//		String sql = " SELECT * FROM sys_user WHERE md5_sys_user_id = ? ";
		String sql = " SELECT * FROM sys_user WHERE salt = ? ";
		List<?> list = jdbctemplate.queryForList(sql,new Object[]{user.getSalt()});
		return list;
	}
	
	/**
	 * 通过用户名称获取用户信息
	 */
	@Override
	public SysUser findAUserByUsername(String username) {
		String sql = " SELECT * FROM sys_user WHERE username = ?";
		SysUser queryForObject = jdbctemplate.queryForObject(sql, new RowMapper<SysUser>(){
			@Override
			public SysUser mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser  user = new SysUser();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt("id"));
				user.setSalt(rs.getString("salt"));
				user.setRealname(rs.getString("realname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setCreateBy(rs.getString("create_by"));
				user.setCreateDate(rs.getTimestamp("create_date"));
				user.setUpdateBy(rs.getString("update_by"));
				user.setUpdateDate(rs.getTimestamp("update_date"));
				user.setRemarks(rs.getString("remarks"));
				user.setDelFlag(rs.getString("del_flag"));
				return user;
			}
		},username);
		return queryForObject;
	}

	@Override
	public List<SysOrganization> queryUserOrganizationByUser(SysUser user) throws ParseException {
		String sql = "SELECT t3.* FROM sys_user t1 JOIN yw_user_organization t2 ON t1.id = t2.user_id JOIN dm_organization t3 ON t2.organization_id = t3.id WHERE t1.id = ?";
		List<Map<String, Object>> rst = jdbctemplate.queryForList(sql, new Object[]{user.getId()});
		List<SysOrganization> organizations = new ArrayList<SysOrganization>();
		for (int i = 0; i < rst.size(); i++) {
			SysOrganization organization = new SysOrganization();
			organization.setId(Integer.parseInt(rst.get(i).get("id").toString()));
			organization.setName(rst.get(i).get("name").toString());
			organization.setParent_id(Integer.parseInt(rst.get(i).get("parent_id").toString()));
			organization.setParent_ids(rst.get(i).get("parent_ids")==null?"":rst.get(i).get("parent_ids").toString());
			organization.setCreate_by(rst.get(i).get("create_by")==null?"":rst.get(i).get("create_by").toString());
			organization.setCreate_date(rst.get(i).get("create_date")==null?null:new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(rst.get(i).get("create_date").toString()));
			organization.setUpdate_by(rst.get(i).get("update_by")==null?"":rst.get(i).get("update_by").toString());
			organization.setUpdate_date(rst.get(i).get("update_date")==null?null:new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(rst.get(i).get("update_date").toString()));
			organization.setDel_flag(rst.get(i).get("del_flag")==null?"":rst.get(i).get("del_flag").toString());
			organization.setRemarks(rst.get(i).get("remarks")==null?"":rst.get(i).get("remarks").toString());
			organizations.add(organization);
		}
		return organizations;
	}
}
