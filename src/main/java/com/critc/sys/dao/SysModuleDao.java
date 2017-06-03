package com.critc.sys.dao;

import com.critc.sys.model.SysModule;
import com.critc.util.dao.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysModuleDao extends BaseDao {

    public int add(SysModule sysModule) {
        String sql = "insert into t_sys_module(name,code,parent_id,url,target,iconImg,display_order) values(:name,:code,:parent_id,:url,:target,:iconImg,:display_order)";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysModule));
    }

    public int update(SysModule sysModule) {
        String sql = "update t_sys_module set name=:name,code=:code,url=:url,parent_id=:parent_id,target=:target,iconImg=:iconImg,display_order=:display_order where id=:id";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysModule));
    }

    public int delete(int id) {
        String sql = "delete from t_sys_module where id=?";
        return jdbcTemplate.update(sql, id);
    }

    public SysModule get(int id) {
        String sql = "select *,(select name from t_sys_module where id=t.parent_id) parent_name from t_sys_module t where id=?";
        Object[] params = new Object[]{id};
        List<SysModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysModule.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public List<SysModule> list() {
        String sql = "select m.*,(select count(*) from t_sys_module where parent_id=m.id) cnt from t_sys_module m order by parent_id, display_order";
        List<SysModule> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SysModule.class));
        return list;
    }

    /**
     * 获取下级节点总数
     *
     * @param id
     * @return
     */
    public int getChildCount(int id) {
        String sql = "select count(*) from t_sys_module where parent_id=?";
        Object[] objects = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, objects, Integer.class);
    }

    /**
     * 根据user_id获取模块
     *
     * @param user_id
     * @return
     */
    public List<SysModule> listByUser_id(int user_id) {
        String sql = "select m.*,(select count(*) from t_sys_module where parent_id=m.id) cnt from t_sys_module m where id"
                + " in (select  module_id from t_sys_rolemodule where role_id in (select role_id from t_sys_user where id =?))" + " order by parent_id, display_order";
        Object[] params = new Object[]{user_id};
        List<SysModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysModule.class));
        return list;
    }

    /**
     * 根据角色id获取模块
     *
     * @param role_id
     * @return
     */
    public List<SysModule> listByRole_id(int role_id) {
        String sql = "select m.* from t_sys_module m";
        sql += " where id in (select  module_id from t_sys_rolemodule where role_id =?)";
        sql += " order by parent_id, display_order";
        Object[] params = new Object[]{role_id};
        List<SysModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysModule.class));
        return list;
    }

    /**
     * 根据模块代码获取模块信息
     *
     * @param code
     * @return
     */
    public SysModule getByModuleCode(String code) {
        String sql = "select * from t_sys_module where code=?";
        Object[] params = new Object[]{code};
        List<SysModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysModule.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 根据角色id获取模块
     *
     * @param url
     * @return
     */
    public SysModule getByUrl(String url) {
        String sql = "select m.* from t_sys_module m where url=? ";
        Object[] params = new Object[]{url};
        List<SysModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysModule.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }
}
