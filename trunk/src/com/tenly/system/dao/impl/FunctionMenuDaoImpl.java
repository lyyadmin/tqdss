package com.tenly.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysMenu;
import com.tenly.system.dao.IFunctionMenuDao;

@Repository
public class FunctionMenuDaoImpl implements IFunctionMenuDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void pageQueryFunctionMenu(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		//查询total---总数据量
		String countSql = "select * from sys_menu_checkout ";
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
		String sql = "select * from sys_menu_checkout limit "+firstResult+","+maxResults;
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, new Object[]{});
		pageBean.setRows(queryForList);
	}

	/**
	 * 查询所有权限
	 */
	@Override
	public List<SysMenu> findAll() {
		String sql = "select * from sys_menu_checkout smenu where smenu.isshow='1' order by smenu.sort asc";
		List<SysMenu> list = jdbcTemplate.query(sql, new RowMapper<SysMenu>(){
			@Override
			public SysMenu mapRow(ResultSet rs, int arg1) throws SQLException {
				SysMenu sysMenu = new SysMenu();
				sysMenu.setCode(rs.getString("code"));
				sysMenu.setName(rs.getString("name"));
				sysMenu.setId(rs.getInt("id"));
				sysMenu.setParentId(rs.getString("parent_id"));
				sysMenu.setParentIds(rs.getString("parent_ids"));
				sysMenu.setPermission(rs.getString("permission"));
				sysMenu.setUrl(rs.getString("url"));
				return sysMenu;
			}
		});
		
		return list;
	}
	/**
	 * 根据用户id查询权限
	 */
	@Override
	public List<SysMenu> findFunctionListByUserId(Integer id) {
		//先查角色
		String userSQL = "select userRole.role_id from sys_user user inner join sys_user_role userRole on user.id=userRole.user_id where user.id="+id;
		int roleId = jdbcTemplate.queryForObject(userSQL,new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt("role_id");
			}
		});
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT smenu.id,smenu. NAME,smenu.parent_id,"
						+"smenu.parent_ids,"
						+"smenu.permission,"
						+"smenu.url,"
						+"smenu.CODE ");
		sb.append("FROM sys_menu_checkout smenu ");
		sb.append(" left join sys_role_menu roleMenu on smenu.id=roleMenu.menu_id where roleMenu.role_id=");
		sb.append(""+roleId);
		sb.append(" AND smenu.isshow = '1' ORDER BY smenu.sort ASC");
		//然后查角色菜单
		
//		StringBuilder sb = new StringBuilder();
//		sb.append("SELECT DISTINCT smenu.id,smenu. NAME,smenu.parent_id,"
//						+"smenu.parent_ids,"
//						+"smenu.permission,"
//						+"smenu.url,"
//						+"smenu.CODE ");
//		sb.append("FROM sys_menu smenu ");
//		sb.append("LEFT OUTER JOIN sys_role_menu srolemenu ON smenu.id = srolemenu.menu_id ");
//		sb.append("LEFT OUTER JOIN sys_role role ON role.id = srolemenu.role_id ");
//		sb.append("LEFT OUTER JOIN sys_user_role suserrole ON role.id = suserrole.role_id ");
//		sb.append("LEFT OUTER JOIN sys_user suser ON suser.id = suserrole.user_id ");
//		sb.append("WHERE suser.id = ? AND smenu.isshow = '1' ORDER BY smenu.sort ASC");
		List<SysMenu> list = jdbcTemplate.query(sb.toString(), new RowMapper<SysMenu>(){
			@Override
			public SysMenu mapRow(ResultSet rs, int arg1) throws SQLException {
				SysMenu sMenu = new SysMenu();
				sMenu.setCode(rs.getString("code"));
				sMenu.setName(rs.getString("name"));
				sMenu.setId(rs.getInt("id"));
				sMenu.setParentId(rs.getString("parent_id"));
				sMenu.setParentIds(rs.getString("parent_ids"));
				sMenu.setPermission(rs.getString("permission"));
				sMenu.setUrl(rs.getString("url"));
				return sMenu;
			}
		});
		return list;
	}
	/**
	 * 添加菜单
	 */
	@Override
	public void addFunctionMenu(SysMenu sysMenu) {
		/**
	 private Integer id;
	private String name;
	private String code;
	private String type;
	private String url;
	private String parentId;
	private String parentIds;
	private String permission;
	private Boolean isshow;
	private Integer sort;
	private String menuIcon;
	private String remarks;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private String delFlag;
		 */
		String insetSql = "insert into sys_menu_checkout (name,code,url,parent_id,isshow,sort,remarks)values(?,?,?,?,?,?,?)";
		Object[] objArr = {sysMenu.getName(),sysMenu.getCode(),sysMenu.getUrl(),sysMenu.getParentId(),sysMenu.getIsshow(),sysMenu.getSort(),sysMenu.getRemarks()};
		int tm = jdbcTemplate.update(insetSql, objArr);
	}
	/**
	 * 更新功能权限菜单
	 */
	@Override
	public void updateFunctionMenu(SysMenu sysMenu) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sdf.format(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		sb.append("update sys_menu_checkout set name='"+sysMenu.getName()+"',code='"+sysMenu.getCode()+"',type='"+sysMenu.getType());
		sb.append("',url='"+sysMenu.getUrl()+"',permission='"+sysMenu.getPermission()+"',remarks='"+sysMenu.getRemarks()+"',update_date='"+format+"' where id="+sysMenu.getId());
		int update = jdbcTemplate.update(sb.toString());
		
	}
	/**
	 * 获取角色的菜单权限ID
	 */
	@Override
	public List<Map<String, Object>> findRoleFunctionMenu(String roleId) {
		String sql = "select menu_id FROM (SELECT id from sys_menu_checkout smenu where smenu.isshow='1' and parent_id != 0)a JOIN "
				+ "(select  menu_id from sys_role_menu WHERE role_id=?)b ON a.id=b.menu_id";
//		System.out.println(sql);
		return jdbcTemplate.queryForList(sql, new Object[]{roleId});
	}
}
