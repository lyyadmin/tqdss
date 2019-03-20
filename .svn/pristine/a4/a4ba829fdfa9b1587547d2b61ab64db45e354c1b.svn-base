package com.tenly.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenly.project.bean.treeBean;
import com.tenly.project.dao.HomePageDao;
import com.tenly.project.service.HomePageService;
import com.tenly.system.bean.SysUser;

@Service
public class HomePageServiceImpl implements HomePageService {
	
	@Autowired
	public HomePageDao homepagedao;
	
	//获取树权限集合
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getSysPointList(String selpoint,String yjlevel) {
		
		List treeLists = new ArrayList();
		List list = homepagedao.getSysPointList(selpoint,yjlevel);
		
		for (int i = 0; i < list.size(); i++) {
			treeBean tb = new treeBean();
			Map map = (Map)list.get(i);
			
			String id = ((Integer)map.get("id")).toString();
			Long pcount = (Long)map.get("pcount");
			tb.setId(id);
			tb.setName((String)map.get("name"));
			tb.setpId(((Integer)map.get("parent_id")).toString());
			if("2".equals(selpoint) && id.length() == 1){
				tb.setNocheck("true");
			}else if("3".equals(selpoint) && id.length() <=3){
				tb.setNocheck("true");
			}
			
			if(pcount > 0){
				tb.setIcon("./css/zTreeStyle/img/diy/8.png");
			}
			treeLists.add(tb);
		}
		
		return treeLists;
	}
	
	@SuppressWarnings("rawtypes")
	public String querySysPointListByYjLevel(Integer userId,String yjlevel,String selpoint){
		String str = "";
		List list = homepagedao.querySysPointListByYjLevel(userId, yjlevel, selpoint);
		if(list != null){
			for (int i = 0; i < list.size(); i++){
				Map map = (Map)list.get(i);
				if("".equals(str)){
					str = ((Integer)map.get("dm_point_id")).toString();
				}else{
					str += "," + ((Integer)map.get("dm_point_id")).toString();
				}
			}
		}
		return str;		
	}
	
	@SuppressWarnings("rawtypes")
	public void saveYjcs(Integer userId,String yjlevel,String selpoint,String pIdstr){
		//获取原有项点类型
		Map map = homepagedao.queryOldYjcs(userId);
		Long pId = (Long)map.get("pi_id");
		Integer point_type = (Integer)map.get("point_type");
		if(!selpoint.equals(point_type.toString())){
			//项点改变删除原有项点关联所有字表记录
			homepagedao.deleteYjcsDetail(pId);
			//修改预警主表项点类型
			homepagedao.updateYjcs(userId, selpoint);
		}else{
			//项点相同删除项点、预警级别关联记录;
			homepagedao.deleteYjcsDetail(pId, yjlevel);
		}

		//删除后添加新预警设置
		String[] pids = pIdstr.split(",");
		for (int i = 0; i < pids.length; i++) {
			homepagedao.addYjcsDetail(pids[i],yjlevel,pId);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map get3DData(SysUser user,String bdate,String edate){
		Map map = new HashMap();
		List olist = new ArrayList();
		List slist = new ArrayList();
		List msglist = new ArrayList();
		List colorList = new ArrayList();
		
		//根据用户获取对应组织架构下级集合
		List list = homepagedao.querySysOrganizationByUser(user);
		
		int c = 0; //每个段位记录三个预警点
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				Map omap = (Map)list.get(i);
				Integer oid = (Integer)omap.get("id"); //组织ID
				String oname = (String)omap.get("name");
				olist.add(oname);
				
				//一个组织记录三种颜色
				colorList.add("#ce230e");
				colorList.add("#0fa516");
				colorList.add("#49a0ea");
				
				Map msgMap = new HashMap();
				List pointlist = new ArrayList();
				msgMap.put("name", oname);
				
				//1、获取组织一级预警内容(该SQL只用了项点3问题的列关联查询的结果、要补充------根据项点选择关联的列)
				List solist1 = new ArrayList();
				Map s1 = homepagedao.getScore(user,"1",oid+"%",bdate,edate);
				solist1.add(i);
				solist1.add(0);
				if(s1 != null){
					solist1.add(s1.get("dScore"));
				}else{
					solist1.add(0);
				}
				slist.add(solist1);
				pointlist.add(c);
				c++;
				
				//2、获取组织二级预警内容
				List solist2 = new ArrayList();
				Map s2 = homepagedao.getScore(user,"2",oid+"%",bdate,edate);
				solist2.add(i);
				solist2.add(1);
				if(s2 != null){
					solist2.add(s2.get("dScore"));
				}else{
					solist2.add(0);
				}
				slist.add(solist2);
				pointlist.add(c);
				c++;
				
				//3、获取组织三级预警内容
				List solist3 = new ArrayList();
				Map s3 = homepagedao.getScore(user,"3",oid+"%",bdate,edate);
				solist3.add(i);
				solist3.add(2);
				if(s3 != null){
					solist3.add(s3.get("dScore"));
				}else{
					solist3.add(0);
				}
				
				slist.add(solist3);
				pointlist.add(c);
				msgMap.put("id", oid);
				msgMap.put("point", pointlist);
				msglist.add(msgMap);
				c++;
			}
		}
		map.put("yStr",olist);
		map.put("zStr",slist);
		map.put("msglist",msglist);
		map.put("colorList",colorList);
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getSysOrganization(SysUser user){
		Map map = homepagedao.getSysOrganization(user);
		return map;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map get3DDataDetail(SysUser user,String selMarkId,String bdate,String edate){
		Map map = new HashMap();
		List olist = new ArrayList();
		List slist = new ArrayList();
		List msglist = new ArrayList();
		List colorList = new ArrayList();
		
		//根据用户获取对应组织架构下级集合
		List list = homepagedao.querySysOrganizationBySelId(selMarkId);
		
		int c = 0; //每个段位记录三个预警点
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				Map omap = (Map)list.get(i);
				Integer oid = (Integer)omap.get("id"); //组织ID
				String oname = (String)omap.get("name");
				olist.add(oname);
				
				//一个组织记录三种颜色
				colorList.add("#ce230e");
				colorList.add("#0fa516");
				colorList.add("#49a0ea");
				
				Map msgMap = new HashMap();
				List pointlist = new ArrayList();
				msgMap.put("name", oname);
				
				//1、获取组织一级预警内容(该SQL只用了项点3问题的列关联查询的结果、要补充------根据项点选择关联的列)
				List solist1 = new ArrayList();
				Map s1 = homepagedao.getScore(user,"1",oid+"%",bdate,edate);
				solist1.add(i);
				solist1.add(0);
				if(s1 != null){
					solist1.add(s1.get("dScore"));
				}else{
					solist1.add(0);
				}
				slist.add(solist1);
				pointlist.add(c);
				c++;
				
				//2、获取组织二级预警内容
				List solist2 = new ArrayList();
				Map s2 = homepagedao.getScore(user,"2",oid+"%",bdate,edate);
				solist2.add(i);
				solist2.add(1);
				if(s2 != null){
					solist2.add(s2.get("dScore"));
				}else{
					solist2.add(0);
				}
				slist.add(solist2);
				pointlist.add(c);
				c++;
				
				//3、获取组织三级预警内容
				List solist3 = new ArrayList();
				Map s3 = homepagedao.getScore(user,"3",oid+"%",bdate,edate);
				solist3.add(i);
				solist3.add(2);
				if(s3 != null){
					solist3.add(s3.get("dScore"));
				}else{
					solist3.add(0);
				}
				
				slist.add(solist3);
				pointlist.add(c);
				msgMap.put("id", oid);
				msgMap.put("point", pointlist);
				msglist.add(msgMap);
				c++;
			}
		}
		map.put("yStr",olist);
		map.put("zStr",slist);
		map.put("msglist",msglist);
		map.put("colorList",colorList);
		return map;		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getParameterInfoByUser(SysUser user){
		Map map = homepagedao.getParameterInfoByUser(user);
		//获取关注的重点问题集合
		Long pi_id = (Long)map.get("pi_id");
		List list = homepagedao.getParameterInfoZTByUser(pi_id);
		for (int i = 1; i <= list.size(); i++) {
			Map ztmap = (Map)list.get(i-1);
			String zd_question = (String)ztmap.get("zd_question");
			map.put("str"+i, zd_question);
		}
		return map;		
	}
	
	@SuppressWarnings("rawtypes")
	public void saveZtFx(Integer userId,String deductScore,String dxquestion,String oldzdq1,String oldzdq2,String oldzdq3,String oldzdq4,String zdq1,String zdq2,String zdq3,String zdq4,String pIdstr1,String pIdstr2,String pIdstr3,String pIdstr4){
		//1、新增parameter_info参数设置主表
		homepagedao.saveZtFx(userId, deductScore, dxquestion);
		
		//获取总体操作评价主表主键
		Map map = homepagedao.getZtFx(userId);
		if(map != null){
			Long pi_id = (Long)map.get("pi_id");
			if(!"".equals(zdq1) && !"".equals(oldzdq1)){
				homepagedao.updateParameterInfoZt(pi_id,oldzdq1,zdq1);
			}else if(!"".equals(zdq1) && "".equals(oldzdq1)){
				homepagedao.insertParameterInfoZt(pi_id,zdq1);
			}else if("".equals(zdq1) && !"".equals(oldzdq1)){
				homepagedao.delParameterInfoZt(pi_id,oldzdq1);
			}
			
			if(!"".equals(zdq2) && !"".equals(oldzdq2)){
				homepagedao.updateParameterInfoZt(pi_id,oldzdq2,zdq2);
			}else if(!"".equals(zdq2) && "".equals(oldzdq2)){
				homepagedao.insertParameterInfoZt(pi_id,zdq2);
			}else if("".equals(zdq2) && !"".equals(oldzdq2)){
				homepagedao.delParameterInfoZt(pi_id,oldzdq2);
			}
			
			if(!"".equals(zdq3) && !"".equals(oldzdq3)){
				homepagedao.updateParameterInfoZt(pi_id,oldzdq3,zdq3);
			}else if(!"".equals(zdq3) && "".equals(oldzdq3)){
				homepagedao.insertParameterInfoZt(pi_id,zdq3);
			}else if("".equals(zdq3) && !"".equals(oldzdq3)){
				homepagedao.delParameterInfoZt(pi_id,oldzdq3);
			}
			
			if(!"".equals(zdq4) && !"".equals(oldzdq4)){
				homepagedao.updateParameterInfoZt(pi_id,oldzdq4,zdq4);
			}else if(!"".equals(zdq4) && "".equals(oldzdq4)){
				homepagedao.insertParameterInfoZt(pi_id,zdq4);
			}else if("".equals(zdq4) && !"".equals(oldzdq4)){
				homepagedao.delParameterInfoZt(pi_id,oldzdq4);
			}
			
			//3、新增parameter_info参数设置子表、项点不为空、则删除对应名称的子表内容、再新增
			if(!"".equals(pIdstr1) && !"undefined".equals(pIdstr1)){
				homepagedao.delParameterInfoZt(pi_id,zdq1);
				this.addParameterInfoZt(pi_id,pIdstr1, zdq1);
			}
			if(!"".equals(pIdstr2) && !"undefined".equals(pIdstr2)){
				homepagedao.delParameterInfoZt(pi_id,zdq2);
				this.addParameterInfoZt(pi_id,pIdstr2, zdq2);
			}
			if(!"".equals(pIdstr3) && !"undefined".equals(pIdstr3)){
				homepagedao.delParameterInfoZt(pi_id,zdq3);
				this.addParameterInfoZt(pi_id,pIdstr3, zdq3);
			}
			if(!"".equals(pIdstr4) && !"undefined".equals(pIdstr4)){
				homepagedao.delParameterInfoZt(pi_id,zdq4);
				this.addParameterInfoZt(pi_id,pIdstr4, zdq4);
			}
		}
	
	}
	
	//新增设置
	private void addParameterInfoZt(Long pi_id,String pIdstr1,String zdq1){
		//删除后添加新设置
		String[] pids = pIdstr1.split(",");
		for (int i = 0; i < pids.length; i++) {
			homepagedao.addParameterInfoZt(pi_id,zdq1,pids[i]);
		}		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getSysPointByZtFxList(String selName){
		List treeLists = new ArrayList();
		List list = homepagedao.getSysPointByZtFxList(selName);
		
		for (int i = 0; i < list.size(); i++) {
			treeBean tb = new treeBean();
			Map map = (Map)list.get(i);
			
			String id = ((Integer)map.get("id")).toString();
			Long pcount = (Long)map.get("pcount");
			tb.setId(id);
			tb.setName((String)map.get("name"));
			tb.setpId(((Integer)map.get("parent_id")).toString());
			if(id.length() <=3){
				tb.setNocheck("true");
			}
			
			if(pcount > 0){
				tb.setIcon("./css/zTreeStyle/img/diy/8.png");
			}
			treeLists.add(tb);
		}
		
		return treeLists;		
	}
	
	@SuppressWarnings("rawtypes")
	public String querySysPointListByZT(Integer userId,String zd_question){
		String str = "";
		List list = homepagedao.querySysPointListByZT(userId, zd_question);
		if(list != null){
			for (int i = 0; i < list.size(); i++){
				Map map = (Map)list.get(i);
				if(map.get("dm_point_id")!=null){
					if("".equals(str)){
						str = ((Integer)map.get("dm_point_id")).toString();
					}else{
						str += "," + ((Integer)map.get("dm_point_id")).toString();
					}
				}
			}
		}
		return str;				
	}
	
	
}
