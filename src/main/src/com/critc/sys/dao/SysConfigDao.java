package com.critc.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.critc.sys.model.SysConfig;

@Repository
public class SysConfigDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 新增
	 * 
	 * @param sysConfig
	 * @return
	 */

	public int add(SysConfig sysConfig) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		String sql = "insert into t_sys_config(syskey,sysvalue,description,display_order) values(:syskey,:sysvalue,:description,:display_order)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysConfig);
		return namedParameterJdbcTemplate.update(sql, paramSource);
	}

	/**
	 * 修改
	 * 
	 * @param sysConfig
	 * @return
	 */
	public int update(SysConfig sysConfig) {
		String sql = "update t_sys_config set sysvalue=:sysvalue,description=:description,display_order=:display_order where syskey=:syskey ";
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysConfig);
		return namedParameterJdbcTemplate.update(sql, paramSource);
	}

	/**
	 * 删除
	 * 
	 * @param syskey
	 * @return
	 */
	public int delete(String syskey) {
		String sql = "delete from t_sys_config  where syskey=? ";
		return jdbcTemplate.update(sql, syskey);
	}

	/**
	 * 列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SysConfig> list() {
		String sql = "select * from t_sys_config where 1=1  order by display_order  ";
		List<SysConfig> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SysConfig.class));
		return list;
	}

	/**
	 * 获取参数信息
	 * @param syskey
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SysConfig get(String syskey) {
		String sql = "select * from t_sys_config where syskey=?";
		Object[] params = new Object[] { syskey };
		List<SysConfig> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysConfig.class));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 获取转化为订单周期
	 * @return
	 */
	public int getTrans_reserve_circle() {
		return Integer.parseInt(get("TRANS_RESERVE_CIRCLE").getSysvalue());
	}

}
