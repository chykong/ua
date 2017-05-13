package com.critc.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.critc.common.vo.ComboboxVO;
import com.critc.util.string.StringUtil;

@Repository
public class CommonDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplateERP;

	/**
	 * 下拉列表
	 * 
	 * @param table_name数据库表名
	 * @param value编号
	 * @param content显示内容
	 * @param order按照什么排序
	 * @param sort 排序是升序还是降序
	 * @param filter过滤条件
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComboboxVO> listCombobox(String table_name, String value, String content, String order, String sort, String condition) {
		String strSql = "select " + value + " value," + content + " content from " + table_name;
		if (StringUtil.isNotNullOrEmpty(condition))
			strSql += " where " + condition;
		if (StringUtil.isNotNullOrEmpty(order))
			strSql += " order by " + order;
		if (StringUtil.isNotNullOrEmpty(sort))
			strSql += sort;
		return jdbcTemplate.query(strSql, new BeanPropertyRowMapper(ComboboxVO.class));
	}
	
	
	/**
	 * 下拉列表框查ERP
	 * @param table_name
	 * @param value
	 * @param content
	 * @param order
	 * @param sort
	 * @param condition
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ComboboxVO> listComboboxByErp(String table_name, String value, String content, String order, String sort, String condition) {
		String strSql = "select " + value + " value," + content + " content from " + table_name;
		if (StringUtil.isNotNullOrEmpty(condition))
			strSql += " where " + condition;
		if (StringUtil.isNotNullOrEmpty(order))
			strSql += " order by " + order;
		if (StringUtil.isNotNullOrEmpty(sort))
			strSql += sort;
		return jdbcTemplateERP.query(strSql, new BeanPropertyRowMapper(ComboboxVO.class));
	}
}
