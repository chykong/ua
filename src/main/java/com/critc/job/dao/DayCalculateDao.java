package com.critc.job.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DayCalculateDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

}
