package com.tenly.system.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysRole;
import com.tenly.system.bean.SysUser;
import com.tenly.system.dao.ISysRoleDao;

@Repository
public class SysRoleDaoImpl implements ISysRoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void pageQuerySysRole(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		
		//查询total---总数据量
		String countSql = "select * from sys_role ";
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
//		String sql = "select * from sys_role where del_flag='0'  limit "+firstResult+","+maxResults;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("a.id, `name`, `code`, is_sys, usable, b.username create_by, create_date, c.username update_by,  update_date,  remarks ");
		sql.append("FROM (");
		sql.append("SELECT id, `name`, `code`, is_sys, usable, create_by, create_date, update_by, update_date, remarks ");
		sql.append("FROM sys_role WHERE del_flag = '0')a, ");
		sql.append("(SELECT id, username FROM sys_user)b, ");
		sql.append("(SELECT id, username FROM sys_user)c ");
		sql.append("WHERE a.create_by = b.id AND a.update_by = c.id");
		
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString(), new Object[]{});
		//将时间格式该为String
		for(int i=0; i<queryForList.size(); i++){
			Map<String, Object> map = queryForList.get(i);
			map.put("create_date", map.get("create_date").toString().replace(" ", "_"));
			map.put("update_date",map.get("update_date").toString().replace(" ", "_"));
		}
		pageBean.setRows(queryForList);
	}

	@Override
	public void updateUserRole(SysRole role, String functionIds,SysUser user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(System.currentTimeMillis());
		//1、更新角色信息
		//2 角色菜单的中间表
		//3 删除角色菜单的中间的信息
		/**
		 * name	varchar	100	0	0	0	0	0	0		0	角色名称	utf8	utf8_general_ci		0	0
			code	varchar	255	0	-1	0	0	0	0		0	英文名称	utf8	utf8_general_ci		0	0
			is_sys	varchar	64	0	-1	0	0	0	0		0	是否系统数据	utf8	utf8_general_ci		0	0
			usable	varchar	64	0	-1	0	0	0	0		0	是否可用	utf8	utf8_general_ci		0	0
			create_by	varchar	64	0	-1	0	0	0	0		0	创建者	utf8	utf8_general_ci		0	0
			create_date	datetime	0	0	-1	0	0	0	0		0	创建时间				0	0
			update_by	varchar	64	0	-1	0	0	0	0		0	更新者	utf8	utf8_general_ci		0	0
			update_date	datetime	0	0	-1	0	0	0	0		0	更新时间				0	0
			remarks	varchar	255	0	-1	0	0	0	0		0	备注信息	utf8	utf8_general_ci		0	0
			del_flag	char	1	0	0	0	0	0	0	'0'	0	删除标记	utf8	utf8_general_ci		0	0
		 */
		//更新角色 信息
//		String updateRoleSQL= "update sys_role set sys_role(name,code,is_sys,usable,create_by,create_date,update_by,update_date,remarks,del_flag)"
//				+ "values(?,?,?,?,?,?,?,?,?,?) where id='"+role.getId()+"'";
		StringBuilder sb = new StringBuilder();
		sb.append("update sys_role set name='"+role.getName()+"'");
		sb.append(",code='"+role.getCode()+"',is_sys='"+role.getIsSys()+"',usable='"+role.getUsable()+"'");
		sb.append(",update_by='"+user.getId()+"'");
		sb.append(",update_date='"+format+"'");
		sb.append(",remarks='"+role.getRemarks()+"'");
		sb.append(" where id="+role.getId());
		jdbcTemplate.update(sb.toString());
		
		if(StringUtils.isNotBlank(functionIds)&&!("").equals(functionIds)){
			String functionSQL = "delete from sys_role_menu where role_id="+role.getId();
			jdbcTemplate.execute(functionSQL);
			
			String[] ids = functionIds.split(",");
			for (String functionId : ids) {
				String addRoleMenuSQL = "insert into sys_role_menu(menu_id,role_id)values(?,?)";
				jdbcTemplate.update(addRoleMenuSQL,new Object[]{Integer.valueOf(functionId),role.getId()});
			}
			
		}
	}
	/**
	 * 添加角色
	 */
	@Override
	public void save(SysRole role, String functionRoleIds, SysUser user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(System.currentTimeMillis());
		/**
		 *      <!-- name    varchar 100 0   0   0   0   0   0       0   角色名称    utf8    utf8_general_ci     0   0
					code    varchar 255 0   -1  0   0   0   0       0   英文名称    utf8    utf8_general_ci     0   0
					is_sys  varchar 64  0   -1  0   0   0   0       0   是否系统数据  utf8    utf8_general_ci     0   0
					usable  varchar 64  0   -1  0   0   0   0       0   是否可用    utf8    utf8_general_ci     0   0
					create_by   varchar 64  0   -1  0   0   0   0       0   创建者 utf8    utf8_general_ci     0   0
					create_date datetime    0   0   -1  0   0   0   0       0   创建时间                0   0
					update_by   varchar 64  0   -1  0   0   0   0       0   更新者 utf8    utf8_general_ci     0   0
					update_date datetime    0   0   -1  0   0   0   0       0   更新时间                0   0
					remarks varchar 255 0   -1  0   0   0   0       0   备注信息    utf8    utf8_general_ci     0   0
					del_flag    char    1   0   0   0   0   0   0   '0' 0   删除标记    utf8    utf8_general_ci     0   0
		 */
		String sql  = "insert into sys_role(name,code,is_sys,usable,create_by,create_date,update_by,update_date,remarks)values("
				+ "'"+role.getName()+"','"+role.getCode()+"','"+role.getIsSys()+"','"+role.getUsable()+"','"+user.getId()+"','"+format+"','"+user.getId()+"','"+format+"','"+role.getRemarks()+"')";
		jdbcTemplate.execute(sql);
		 
		//获取角色id   TODO  待优化
		String IdSQl = "select max(id) id from sys_role";
		Integer id = jdbcTemplate.queryForObject(IdSQl, int.class);
		//为角色添加权限菜单
		String addSQl = "";
		if(StringUtils.isNotBlank(functionRoleIds)&&!"".equals(functionRoleIds)){
			String[] split = functionRoleIds.split(",");
			for(String menuid:split){
				addSQl = "insert into sys_role_menu(menu_id,role_id)values("+Integer.valueOf(menuid)+","+id+")";
				jdbcTemplate.execute(addSQl);
			}
		}
		
	}

	@Override
	public void delRole(String role) {
		StringBuilder sb = new StringBuilder();
		sb.append("update sys_role set del_flag='1'");
		sb.append(" where id in("+role+")");
		jdbcTemplate.update(sb.toString());
		//删除权限
//		String functionSQL = "delete from sys_role_menu where role_id="+role;
//		jdbcTemplate.execute(functionSQL);
		
	}
}











