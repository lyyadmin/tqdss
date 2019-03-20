package com.tenly.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenly.project.service.ICheckoutDailyDiligenceService;
import com.tenly.system.bean.PageBean;

@Controller
@RequestMapping("/checkout/checkoutDailyDiligence")
public class CheckoutDailyDiligenceController {
	@Autowired
	private ICheckoutDailyDiligenceService checkoutDailyDiligenceService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/dailyDiligence")
	public String dailyDiligence(HttpServletRequest request,HttpServletResponse response){
		return "/project/lzm/checkout-analysis/checkout-daily-diligence";
	}
	/*
	 * 查询司机退勤文件缺失原因到日勤
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/findAll")
	@ResponseBody
	public PageBean findAll(HttpServletRequest request,HttpServletResponse response){
		PageBean pb = new PageBean();
		List<Map<String,Object>> list= checkoutDailyDiligenceService.findAll();
		pb.setRows(list);
		return pb;
	}
	
}
