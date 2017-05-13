package com.critc.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.critc.sys.model.SysUserLogin;
import com.critc.util.page.PageUtil;

@Repository
public class SysUserLoginDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void add(SysUserLogin sysUserLogin) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		String sql = "insert into t_sys_userlogin(user_id,login_date,login_ip,terminal,explorerType,explorerVersion)";
		sql += " values(:user_id,:login_date,:login_ip,:terminal,:explorerType,:explorerVersion)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysUserLogin);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}

	/**
	 * 取得最后登录信息
	 * @param user_id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SysUserLogin getLastLogin(int user_id) {
		String sql = "select * from t_sys_userlogin where user_id=?";
		Object[] params = new Object[] { user_id };
		List<SysUserLogin> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysUserLogin.class));
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SysUserLogin> list(int user_id, int pageIndex, int pageSize) {
		String sql = "select * from t_sys_userlogin where user_id=? order by login_date desc ";
		sql = PageUtil.createMysqlPageSql(sql, pageIndex, pageSize);
		Object[] params = new Object[] { user_id };
		List<SysUserLogin> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysUserLogin.class));
		return list;
	}

	/**
	 * 查询用户登录总数
	 * 
	 * @param sysUserSearchVO
	 * @return
	 */
	public int listCount(int user_id) {
		String sql = "select count(*) from t_sys_userlogin where user_id=? order by login_date desc ";
		Object[] params = new Object[] { user_id };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}

}
