package com.tenly.project.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tenly.project.dao.ICheckoutDailyDiligenceDao;
@Repository
public class CheckoutDailyDiligenceDaoImpl implements
		ICheckoutDailyDiligenceDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> findAll() {
		String sql = "select * from yw_checkout_filelose_cause";
		return jdbcTemplate.queryForList(sql);
	}

}
