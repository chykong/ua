package com.critc.sys.dao;

import com.critc.sys.model.SysLog;
import com.critc.sys.vo.SysLogSearchVO;
import com.critc.util.page.PageUtil;
import com.critc.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统日志dao
 * @author  孔垂云
 *
 */
@Repository
public class SysLogDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int add(SysLog sysLog) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		String sql = "insert into t_sys_log(user_id,opera_date,opera_ip,module_name,opera_name,opera_url,opera_type,opera_params)"
				+ " values(:user_id,:opera_date,:opera_ip,:module_name,:opera_name,:opera_url,:opera_type,:opera_params)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysLog);
		return namedParameterJdbcTemplate.update(sql, paramSource);
	}

	/**
	 * 查询
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SysLog> list(SysLogSearchVO sysLogSearchVO, int pageIndex, int pageSize) {
		String sql = "select l.*,u.realname realname  from t_sys_log l,t_sys_user u where l.user_id=u.id  ";
		sql += createSearchSql(sysLogSearchVO);
		sql += " order by opera_date desc";
		sql = PageUtil.createMysqlPageSql(sql, pageIndex, pageSize);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysLogSearchVO);
		List<SysLog> list = namedParameterJdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper(SysLog.class));
		return list;
	}

	/**
	 * 查询所有，不分页
	 * @param sysLogSearchVO
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SysLog> list(SysLogSearchVO sysLogSearchVO) {
		String sql = "select l.*,u.code user_code,u.realname realname  from t_sys_log l,t_sys_user u where l.user_id=u.id  ";
		sql += createSearchSql(sysLogSearchVO);
		sql += " order by opera_date desc";
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysLogSearchVO);
		List<SysLog> list = namedParameterJdbcTemplate.query(sql, paramSource, new BeanPropertyRowMapper(SysLog.class));
		return list;
	}

	/**
	 * 查询
	 * 
	 * @param sysLogSearchVO
	 * @return
	 */
	public int listCount(SysLogSearchVO sysLogSearchVO) {
		String sql = "select count(*) from t_sys_log where 1=1 ";
		sql += createSearchSql(sysLogSearchVO);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sysLogSearchVO);
		return namedParameterJdbcTemplate.queryForObject(sql, paramSource, Integer.class);
	}

	private String createSearchSql(SysLogSearchVO sysLogSearchVO) {
		String sql = "";
		if (sysLogSearchVO.getUser_id() != null) {
			sql += " and user_id=:user_id";
		} else {
			sql += " and user_id=0";
		}
		if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getS_date())) {
			sql += " and date(opera_date)>=:s_date";
		}
		if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getE_date())) {
			sql += " and date(opera_date)<=:e_date";
		}
		return sql;
	}
}
