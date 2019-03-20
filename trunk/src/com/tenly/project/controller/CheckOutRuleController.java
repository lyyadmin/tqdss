package com.tenly.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenly.project.bean.CheckOutRule;
import com.tenly.project.bean.ItemsMeasures;
import com.tenly.project.bean.LoseCause;
import com.tenly.project.service.ICheckOutRuleService;
import com.tenly.system.bean.PageBean;
import com.tenly.system.bean.SysUser;

@Controller
@RequestMapping("/checkout/checkoutrule")
public class CheckOutRuleController {
	@Autowired
	private ICheckOutRuleService checkOutService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/checkOutRule")
	public String checkOutRule(){
		return "/project/lzm/checkout-analysis/checkout-rule-update";
	}
	/**
	 * 暂未使用
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryForItems")
	public List<Map<String,Object>> queryForItems(){
		List<Map<String,Object>> list =null;
			try{
				list = checkOutService.queryForItems();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return list;
	}
	
	/**
	 * 保存过滤条件
	 * @param rule
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRule")
	public String saveRule(CheckOutRule rule){
		String success = "";
			try{
				checkOutService.saveRule(rule);
				success="1";
			}catch(Exception e){
				e.printStackTrace();
				success = "0";
			}
		
		return success;
	}
	/**
	 * 查询所有退勤的规则集
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllCondition")
	public PageBean findAllCondition(){
		PageBean pageBean = new PageBean();
		List<Map<String, Object>> list=null;
		try {
			list = checkOutService.findAllCondition();
			pageBean.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean;
	}
	
	/**
	 * 保存规则集
	 * @param rule
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCondtion",produces="text/html;charset=utf-8")
	public String addCondtion(CheckOutRule rule){
		String res = "";
		try{
			checkOutService.addCondtion(rule);
			res="1";
		}catch(Exception e){
			e.printStackTrace();
			res="0";
		}
		return res;
	}
	/**
	 * 更新规则集
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCondtion",produces="text/html;charset=utf-8")
	public String updateCondtion(CheckOutRule rule){
		String res = "";
		try{
			if("启用".equals(rule.getConType())){
				rule.setConType("1");
			}else{
				rule.setConType("0");
			}
			checkOutService.updateCondtion(rule);
			res="1";
		}catch(Exception e){
			e.printStackTrace();
			res="0";
		}
		return res;
	}
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value="/delCondition",produces="text/html;charset=utf-8")
	public String delCondition(CheckOutRule rule){
		String res = "";
		try{
			checkOutService.delCondition(rule);
			res="1";
		}catch(Exception e){
			e.printStackTrace();
			res="0";
		}
		return res;
	}
	
	
	
	/**
	 * 文件缺失原因和配置异议
	 */
	@ResponseBody
	@RequestMapping("/findAllCause")
	public PageBean findAllCause(CheckOutRule rule){
		PageBean pb = new PageBean();
		List<Map<String, Object>> res = null;
		try{
			res = checkOutService.findAllCause();
		}catch(Exception e){
			e.printStackTrace();
		}
		pb.setRows(res);
		return pb;
	}
	
	/**
	 * 文件缺失原因和配置异议
	 */
	@ResponseBody
	@RequestMapping("/updateCause")
	public String updateCause(LoseCause loseCause){
		String res = "";
		try{
			checkOutService.updateCause(loseCause);
			res = "1";
		}catch(Exception e){
			res = "0";
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 文件缺失原因和配置异议
	 */
	@ResponseBody
	@RequestMapping("/findAllExceptionCause")
	public PageBean findAllExceptionCause(){
		PageBean pb = new PageBean();
		try{
			List<Map<String,Object>> list = checkOutService.findAllExceptionCause();
			pb.setRows(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pb;
	}
	/**
	 * 文件缺失原因和配置异议
	 */
	@ResponseBody
	@RequestMapping("/getLoseFileCause")
	public List<Map<String,Object>> getLoseFileCause(){
		List<Map<String,Object>> list = null;
		try{
			 list = checkOutService.getLoseFileCause();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 查询所有的项点
	 */
	@ResponseBody
	@RequestMapping("/findAllItem")
	public PageBean findAllItem(){
		PageBean pb = new PageBean();
		try{
			List<Map<String, Object>> loseFileCause = checkOutService.findAllItem();
			pb.setRows(loseFileCause);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pb;
	}
	/**
	 * 保存项点的处理措施
	 */
	@ResponseBody
	@RequestMapping("/saveItems")
	public String saveItems(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody ItemsMeasures[] rowObj){
		String res = "";
		try{
			checkOutService.saveItems(rowObj);
			res="1";
		}catch(Exception e){
			res="0";
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 保存文件异常原因
	 */
	@ResponseBody
	@RequestMapping("/saveFileExceptionCause")
	public String saveFileExceptionCause(HttpServletRequest request,
			HttpServletResponse response,LoseCause cause){
		String res = "";
		try{
			checkOutService.saveFileExceptionCause(cause);
			res="1";
		}catch(Exception e){
			res="0";
			e.printStackTrace();
		}
		return res;
	}
	
	//------------------------------规则树-------------------------------syspointlist
	/**
	 * 初始化树结构
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/syspointlist")
	public List syspointlist(HttpServletRequest request,
			HttpServletResponse response,String selpoint,String yjlevel) throws Exception{
		//获取当前用户
		return checkOutService.getSysPointList(selpoint,yjlevel);
	}
	
	/**
	 * 级别变化
	 */
	@ResponseBody
	@RequestMapping("/changeyjlevel")
	public Map<String,Object> changeyjlevel(HttpServletRequest request,
			HttpServletResponse response,String yjlevel, String selpoint){
		Map<String,Object> map = new HashMap<String,Object>();
		String str = checkOutService.querySysPointListByYjLevel(yjlevel,selpoint);
		map.put("status","success");
		map.put("value",str);
		return map;
	}
	/**
	 * 保存配置的项点级别
	 */
	@ResponseBody
	@RequestMapping("/saveyjcs")
	public String saveyjcs(HttpServletRequest request,
			HttpServletResponse response, String yjlevel, String selpoint,String pIdstr,String measure){
		String rs = "";
		try{
			checkOutService.saveYjcs(yjlevel,selpoint,pIdstr,measure);
			rs="1";
		}catch(Exception e){
			rs = "0";
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 删除文件缺失和有无异议的数据
	 */
	@ResponseBody
	@RequestMapping("/delCauseRow")
	public String delCauseRow(String loseID){
		String rs = "";
		try{
			checkOutService.delCauseRow(loseID);
			rs="1";
		}catch(Exception e){
			rs = "0";
			e.printStackTrace();
		}
		return rs;
	}
	
	
}









