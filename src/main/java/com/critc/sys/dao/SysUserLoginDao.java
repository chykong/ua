package com.critc.sys.dao;

import com.critc.sys.model.SysUserLogin;
import com.critc.util.dao.BaseDao;
import com.critc.util.page.PageUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysUserLoginDao extends BaseDao {

    public void add(SysUserLogin sysUserLogin) {
        String sql = "insert into t_sys_userlogin(user_id,login_date,login_ip,terminal,explorerType,explorerVersion)";
        sql += " values(:user_id,:login_date,:login_ip,:terminal,:explorerType,:explorerVersion)";
        getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysUserLogin));
    }

    /**
     * 取得最后登录信息
     *
     * @param user_id
     * @return
     */
    public SysUserLogin getLastLogin(int user_id) {
        String sql = "select * from t_sys_userlogin where user_id=?";
        Object[] params = new Object[]{user_id};
        List<SysUserLogin> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysUserLogin.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public List<SysUserLogin> list(int user_id, int pageIndex, int pageSize) {
        String sql = "select * from t_sys_userlogin where user_id=? order by login_date desc ";
        sql = PageUtil.createMysqlPageSql(sql, pageIndex, pageSize);
        Object[] params = new Object[]{user_id};
        List<SysUserLogin> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysUserLogin.class));
        return list;
    }

    /**
     * 查询用户登录总数
     *
     * @param user_id
     * @return
     */
    public int listCount(int user_id) {
        String sql = "select count(*) from t_sys_userlogin where user_id=? order by login_date desc ";
        Object[] params = new Object[]{user_id};
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

}
