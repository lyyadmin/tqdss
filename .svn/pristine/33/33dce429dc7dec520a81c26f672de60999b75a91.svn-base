package com.tenly.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tenly.common.systools.StringTools;
import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysOrganization;
import com.tenly.system.bean.SysRole;
import com.tenly.system.bean.SysUser;
import com.tenly.system.dao.IUserManagerDao;

@Repository
public class UserManagerDaoImpl implements IUserManagerDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 用户的分页查询
	 */
	@Override
	public void pageQueryUserInfo(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		
		//查询total---总数据量
		String countSql = "select * "
				+ "from sys_user user inner join sys_user_role user_role on user.id=user_role.user_id inner join sys_role role on user_role.role_id = role.id";
		int count = jdbcTemplate.queryForList(countSql).size();
		pageBean.setRecords(count);
		if((count/pageSize)<0){
			pageBean.setTotal(1);
		}else{
			pageBean.setTotal(count/pageSize+1);
		}
		//查询rows---当前页需要展示的数据集合
		int firstResult = (currentPage - 1) * pageSize;
		int maxResults = pageSize;
		//开始查询
		String sql = "SELECT USER .id, USER .realname, USER .username, USER .portrait, USER .email, USER .phone, "
				+ "DATE_FORMAT( USER .create_date, '%Y-%m-%d %T' ) create_date, DATE_FORMAT( USER .update_date, '%Y-%m-%d %T' ) update_date, "
				+ "USER .remarks, CASE WHEN USER .del_flag = 0 THEN '否' ELSE '是' END AS del_flag, role. name, role. code, dm_organization.id organization_id, dm_organization. NAME organization_name "
				+ "FROM sys_user USER INNER JOIN sys_user_role user_role ON USER .id = user_role.user_id INNER JOIN sys_role role ON user_role.role_id = role.id "
				+ "JOIN yw_user_organization ON USER .id = yw_user_organization.user_id JOIN dm_organization ON yw_user_organization.organization_id"
				+ " = dm_organization.id LIMIT ?, ?";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, new Object[]{firstResult, maxResults});
		pageBean.setRows(queryForList);
	}

	@Override
	public List<SysRole> queryRoles() {
		String sql = "select role.id,role.name,role.code from sys_role role";
		List<SysRole> list = jdbcTemplate.query(sql, new RowMapper<SysRole>(){
			@Override
			public SysRole mapRow(ResultSet rs, int arg1) throws SQLException {
				SysRole role = new SysRole();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setName(rs.getString("code"));
				return role;
			}
			
		});
		return list;
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public void updateUser(SysUser user, String role) {
		/**
		 * private Integer id;
	private String realname;
	private String username;
	private String portrait;
	private String password;
	private String salt;
	private String email;
	private String phone;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private String remarks;
	private String delFlag;
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sdf.format(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		sb.append("update sys_user set username='"+user.getUsername()+"',password='"+StringTools.md5(user.getPassword())+"',");
		sb.append("realname='"+user.getRealname()+"',portrait='"+user.getPortrait()+"',");
		sb.append("email='"+user.getEmail()+"',phone='"+user.getPhone()+"',");
		sb.append("create_date='"+format+"',");
		sb.append("update_date='"+format+"',remarks='"+user.getRemarks()+"' where id="+user.getId());
		jdbcTemplate.update(sb.toString());
		
		String rolesql = "update sys_user_role set role_id="+Integer.valueOf(role)+" where user_id="+user.getId();
		
		jdbcTemplate.update(rolesql);
	}

	@Override
	public void save(SysUser user,String role) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sdf.format(System.currentTimeMillis());
		
		//用户信息
		StringBuilder sb  = new StringBuilder();
		sb.append("insert into sys_user(realname,username,portrait,password,salt,email,"
		+ "phone,create_by,create_date,update_by,update_date,remarks,del_flag) values('"+user.getRealname()
		+"','"+user.getUsername()+"',"+user.getPortrait()+",'"+StringTools.md5(user.getPassword())+"',"
				+ ""+user.getSalt()+","+user.getEmail()+","+user.getPhone()+","+user.getCreateBy()
				+",'"+format+"',"+user.getUpdateBy()+",'"+format+"','"+user.getRemarks()+"','1')");
		jdbcTemplate.execute(sb.toString());
		
		//当前用户插入的id
		String sql = "select max(id) id from sys_user";
		Integer userid = jdbcTemplate.queryForObject(sql, Integer.class);
		
		//用户关联角色
		String roleSql = "insert into sys_user_role(user_id,role_id)values("+userid+","+Integer.valueOf(role)+")";
		jdbcTemplate.execute(roleSql);
	}

	@Override
	public List<SysOrganization> getAllOrganizations() {
		String sql = "SELECT id, name, parent_id FROM dm_organization";
		List<SysOrganization> organizations = jdbcTemplate.query(sql, new RowMapper<SysOrganization>(){

			@Override
			public SysOrganization mapRow(ResultSet rst, int arg1)
					throws SQLException {
				SysOrganization sysOrganization = new SysOrganization();
				sysOrganization.setId(rst.getInt("id"));
				sysOrganization.setName(rst.getString("name"));
				sysOrganization.setParent_id(rst.getInt("parent_id"));
				return sysOrganization;
			}
			
		});
		return organizations;
	}
}
















