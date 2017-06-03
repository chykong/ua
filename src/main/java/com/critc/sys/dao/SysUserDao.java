package com.critc.sys.dao;

import com.critc.common.vo.ComboboxVO;
import com.critc.sys.model.SysUser;
import com.critc.sys.vo.SysUserSearchVO;
import com.critc.util.dao.BaseDao;
import com.critc.util.page.PageUtil;
import com.critc.util.string.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysUserDao extends BaseDao {

    public int add(SysUser sysUser) {
        String sql = "insert into t_sys_user(username,password,randomcode,status,realname,create_date,create_person,role_id,type)";
        sql += " values(:username,:password,:randomcode,1,:realname,now(),:create_person,:role_id,:type)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rc = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysUser), keyHolder);
        if (rc > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return 0;
        }
    }

    public int update(SysUser sysUser) {
        String sql = "update t_sys_user set realname=:realname,role_id=:role_id where id=:id ";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysUser));
    }

    /**
     * 修改密码
     *
     * @param id
     * @param newPass
     * @param randowmcode
     * @return
     */
    public int updatePass(int id, String newPass, String randowmcode) {
        String sql = "update t_sys_user set password=?,randomcode=?  where id=? ";
        Object[] objects = new Object[]{newPass, randowmcode, id};
        return jdbcTemplate.update(sql, objects);
    }

    /**
     * 修改个人信息，用户自己操作
     *
     * @param sysUser
     * @return
     */
    public int updateInfo(SysUser sysUser) {
        String sql = "update t_sys_user set realname=:realname,telephone=:telephone where id=:id";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysUser));
    }

    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(int id, int status) {
        String sql = "update t_sys_user set status=?  where id=?";
        Object[] objects = new Object[]{status, id};
        return jdbcTemplate.update(sql, objects);
    }

    public int delete(int id) {
        String sql = "delete from t_sys_user where id=?";
        return jdbcTemplate.update(sql, id);
    }

    public SysUser get(int id) {
        String sql = "select * from t_sys_user where id=?";
        Object[] params = new Object[]{id};
        List<SysUser> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysUser.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 根据username获取sysUser
     *
     * @param username
     * @return
     */
    public SysUser getByUsername(String username) {
        String sql = "select *,(select name from t_sys_role where id=role_id) role_name from t_sys_user where username=?";
        Object[] params = new Object[]{username};
        List<SysUser> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysUser.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 查询该用户名是否已存在,根据email来判断
     *
     * @param username
     * @return
     */
    public int getUsernameCount(String username) {
        String sql = "select count(*) from t_sys_user where username=? ";
        Object[] objects = new Object[]{username};
        return jdbcTemplate.queryForObject(sql, objects, Integer.class);
    }

    /**
     * 查询用户信息
     *
     * @param sysUserSearchVO
     * @return
     */
    public List<SysUser> list(SysUserSearchVO sysUserSearchVO) {
        String sql = "select *," + "(select name from t_sys_role where id=role_id) role_name  from t_sys_user where 1=1 ";
        sql += createSearchSql(sysUserSearchVO);
        sql += " order by id asc";
        sql = PageUtil.createMysqlPageSql(sql, sysUserSearchVO.getPageIndex(), sysUserSearchVO.getPageSize());
        List<SysUser> list = getNamedParameterJdbcTemplate().query(sql, new BeanPropertySqlParameterSource(sysUserSearchVO), new BeanPropertyRowMapper<>(SysUser.class));
        return list;
    }

    public List<SysUser> listAll() {
        String sql = "select *,(select name from t_sys_role where id=role_id) role_name  from t_sys_user  ";
        sql += " order by id asc";
        List<SysUser> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SysUser.class));
        return list;
    }

    /**
     * 查询用户总数
     *
     * @param sysUserSearchVO
     * @return
     */
    public int listCount(SysUserSearchVO sysUserSearchVO) {
        String sql = "select count(*) from t_sys_user where 1=1 ";
        sql += createSearchSql(sysUserSearchVO);
        return getNamedParameterJdbcTemplate().queryForObject(sql, new BeanPropertySqlParameterSource(sysUserSearchVO), Integer.class);
    }

    private String createSearchSql(SysUserSearchVO sysUserSearchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUsername())) {
            sql += " and username=:username";
        }
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getRealname())) {
            sql += " and realname like :realnameStr";
        }
        if (sysUserSearchVO.getDepartment_id() != null) {
            sql += " and department_id =:department_id";
        }
        if (sysUserSearchVO.getRole_id() != null) {
            sql += " and role_id=:role_id";
        }
        if (sysUserSearchVO.getStatus() != null) {
            sql += " and status=:status";
        }
        return sql;
    }

    /**
     * 所有人员列表，查询日志使用
     *
     * @return
     */
    public List<ComboboxVO> listAllUser() {
        String sql = "select id value,username content from t_sys_user  order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComboboxVO.class));
    }


}
