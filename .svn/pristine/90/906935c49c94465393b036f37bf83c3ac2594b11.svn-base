package com.tenly.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenly.project.bean.CheckOutRule;
import com.tenly.project.bean.ItemsMeasures;
import com.tenly.project.bean.LoseCause;
import com.tenly.project.dao.ICheckOutRuleDao;
import com.tenly.project.service.ICheckOutRuleService;

@Service
public class CheckOutRuleServiceImpl implements ICheckOutRuleService {
	
	@Autowired
	private ICheckOutRuleDao checkOutRuleDao;
	
	@Override
	public void saveRule(CheckOutRule rule) throws Exception {
		checkOutRuleDao.saveRule(rule);
	}

	@Override
	public List<Map<String, Object>> queryForItems() throws Exception {
		return checkOutRuleDao.queryForItems();
	}

	@Override
	public List<Map<String, Object>> findAllCondition() throws Exception {
		return checkOutRuleDao.findAllCondition();
	}

	@Override
	public void addCondtion(CheckOutRule rule) throws Exception {
		checkOutRuleDao.addCondtion(rule);
	}

	@Override
	public void updateCondtion(CheckOutRule rule) throws Exception {
		checkOutRuleDao.checkOutRuleDao(rule);
	}

	@Override
	public void delCondition(CheckOutRule rule) throws Exception {
		checkOutRuleDao.delCondition(rule);
		
	}

	@Override
	public List<Map<String, Object>> findAllCause() throws Exception {
		return checkOutRuleDao.findAllCause();
	}

	@Override
	public void updateCause(LoseCause loseCause) throws Exception {
		checkOutRuleDao.updateCause(loseCause);
	}

	@Override
	public List<Map<String, Object>> findAllExceptionCause() throws Exception {
		return checkOutRuleDao.findAllExceptionCause();
	}

	@Override
	public List<Map<String, Object>> getLoseFileCause() throws Exception {
		return checkOutRuleDao.getLoseFileCause();
	}

	@Override
	public List<Map<String, Object>> findAllItem() throws Exception {
		return checkOutRuleDao.findAllItem();
	}

	@Override
	public void saveItems(ItemsMeasures[] rowObj) throws Exception {
		checkOutRuleDao.saveItems(rowObj);
		
	}

	@Override
	public void saveFileExceptionCause(LoseCause cause) throws Exception {
		checkOutRuleDao.saveFileExceptionCause(cause);
	}

	@Override
	public String querySysPointListByYjLevel(String yjlevel,
			String selpoint) {
		String str = "";
		List list = checkOutRuleDao.querySysPointListByYjLevel(yjlevel, selpoint);
		if(list != null){
			for (int i = 0; i < list.size(); i++){
				Map map = (Map)list.get(i);
				if("".equals(str)){
					str = ((Integer)map.get("sys_point_id")).toString();
				}else{
					str += "," + ((Integer)map.get("sys_point_id")).toString();
				}
			}
		}
		return str;	
	}

	@Override
	public void saveYjcs(String yjlevel, String selpoint,
			String pIdstr,String measure) throws Exception {
		//获取原有项点类型
		List<Map<String, Object>> findAllParameterInfo = checkOutRuleDao.findAllParameterInfo();
		//判断作业项点
		String[] splits = pIdstr.split(",");
		if(findAllParameterInfo==null||findAllParameterInfo.size()<1){
			for(String pointId:splits){
				checkOutRuleDao.addPoint(yjlevel,pointId,measure,selpoint);
			}
		}else{
			int pointType = (Integer)findAllParameterInfo.get(0).get("point_type");
			int selpointType= Integer.valueOf(selpoint);
			if(pointType==selpointType){
				//如果相同，然后判断级别   遍历项点
				//1、先删除级别  by yjlevel
				checkOutRuleDao.deleteYjLevel(yjlevel);
				for(String pointId:splits){
					//2、当前级别之外的当前项点是否存在   如果存在则删除(当前级别是A 当前项点是1 查B、C级别中有没有项点1的记录 有则删除B、C级别项点1的记录);
					checkOutRuleDao.queryByYjLevel(yjlevel,pointId);//不等于级别，等于项点的删除
					//3、然后进行添加
					checkOutRuleDao.addPoint(yjlevel,pointId,measure,selpoint);
				}
			}else{
				//直接清空表，循环进行添加
				checkOutRuleDao.deleteAll();
				//添加
				for(String pointId:splits){
					checkOutRuleDao.addPoint(yjlevel,pointId,measure,selpoint);
				}
			}
		}
	}

	@Override
	public void delCauseRow(String loseID) throws Exception {
		checkOutRuleDao.delCauseRow(loseID);
	}

	@Override
	public List getSysPointList(String selpoint, String yjlevel)
			throws Exception {
		// TODO Auto-generated method stub
		return checkOutRuleDao.getSysPointList( selpoint,  yjlevel);
	}

}
