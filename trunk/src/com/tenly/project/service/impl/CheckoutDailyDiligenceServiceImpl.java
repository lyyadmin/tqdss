package com.tenly.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenly.project.dao.ICheckoutDailyDiligenceDao;
import com.tenly.project.service.ICheckoutDailyDiligenceService;

@Service
public class CheckoutDailyDiligenceServiceImpl implements
		ICheckoutDailyDiligenceService {
	@Autowired
	private ICheckoutDailyDiligenceDao checkoutDailyDiligenceDao;
	
	@Override
	public List<Map<String, Object>> findAll() {
		return checkoutDailyDiligenceDao.findAll();
	}

}
