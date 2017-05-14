package com.critc.sys.dao;

import com.critc.sys.model.SysFunction;
import com.critc.sys.model.SysRoleFunction;
import com.critc.util.dao.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class SysFunctionDao extends BaseDao {

    public int add(SysFunction sysFunction) {
        String sql = "insert into t_sys_function(module_id,name,code,url,type,description,display_order) values(:module_id,:name,:code,:url,:type,:description,:display_order)";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysFunction));
    }

    public int update(SysFunction sysFunction) {
        String sql = "update t_sys_function set module_id=:module_id,name=:name,code=:code,url=:url,type=:type,description=:description,display_order=:display_order where id=:id";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysFunction));
    }

    public int delete(int id) {
        String sql = "delete from t_sys_function where id=?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * 按模块id删除
     *
     * @param module_id
     * @return
     */
    public int deleteByModule_id(int module_id) {
        String sql = "delete from t_sys_function where module_id=?";
        return jdbcTemplate.update(sql, module_id);
    }

    public SysFunction get(int id) {
        String sql = "select * from t_sys_function where id=?";
        Object[] params = new Object[]{id};
        List<SysFunction> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysFunction.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    public List<SysFunction> list(int module_id) {
        String sql = "select * from t_sys_function  where module_id=? order by display_order";
        Object[] objects = new Object[]{module_id};
        List<SysFunction> list = jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper<>(SysFunction.class));
        return list;
    }

    public List<SysFunction> list() {
        String sql = "select * from t_sys_function  order by display_order";
        List<SysFunction> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SysFunction.class));
        return list;
    }

    /**
     * 根据角色获取功能
     *
     * @param role_id
     * @return
     */
    public List<SysRoleFunction> listRoleFunctionByRole_id(int role_id) {
        String sql = "select * from t_sys_rolefunction  where role_id=?";
        Object[] objects = new Object[]{role_id};
        List<SysRoleFunction> list = jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper<>(SysRoleFunction.class));
        return list;
    }

    /**
     * 根据角色获取功能,返回hashmap
     * 缓存设置
     *
     * @param role_id
     * @return
     */
    public HashMap<String, String> hashRoleFunctionByRole_id(int role_id) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String sql = "select * from t_sys_function where id in (select function_id from t_sys_rolefunction  where role_id=?)";
        Object[] objects = new Object[]{role_id};
        List<SysFunction> list = jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper<>(SysFunction.class));
        for (SysFunction sysFunction : list) {
            hashMap.put(sysFunction.getCode(), sysFunction.getUrl());
        }
        return hashMap;
    }

    /**
     * 根据模块获取功能
     *
     * @param module_id
     * @return
     */
    public List<SysFunction> listByModule_id(int module_id) {
        String sql = "select * from t_sys_function  where module_id=? order by display_order";
        Object[] objects = new Object[]{module_id};
        List<SysFunction> list = jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper<>(SysFunction.class));
        return list;
    }

    /**
     * 根据模块获取所有写操作按钮功能
     *
     * @param module_id
     * @return
     */
    public List<SysFunction> listWriteByModule_id(int module_id) {
        String sql = "select * from t_sys_function  where module_id=? and type=1 order by display_order";
        Object[] objects = new Object[]{module_id};
        List<SysFunction> list = jdbcTemplate.query(sql, objects, new BeanPropertyRowMapper<>(SysFunction.class));
        return list;
    }

    /**
     * 根据角色id获取模块
     *
     * @param role_id
     * @return
     */
    public List<SysFunction> listByRole_id(int role_id) {
        String sql = "select m.* from t_sys_function m where id in (select  function_id from t_sys_rolefunction where role_id =?) ";
        Object[] params = new Object[]{role_id};
        List<SysFunction> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysFunction.class));
        return list;
    }

    /**
     * 取只读功能的所有按钮id
     *
     * @param moduleArrIn
     * @return
     */
    public List<Integer> listReadByModule_id(String moduleArrIn) {
        String sql = "select id from t_sys_function  where module_id in (" + moduleArrIn + ") and type=0 order by display_order";
        List<Integer> list = jdbcTemplate.queryForList(sql, Integer.class);
        return list;
    }

    /**
     * 根据url来获取funtion信息,只返回type=1即写操作
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SysFunction> getAll() {
        String sql = "select *,(select name from t_sys_module where id=module_id) module_name from t_sys_function where type=1 ";
        List<SysFunction> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SysFunction.class));
        return list;
    }

    /**
     * 根据按钮代码取按钮
     *
     * @param code
     * @return
     */
    public SysFunction getByCode(String code) {
        String sql = "select * from t_sys_Function where code=?";
        Object[] params = new Object[]{code};
        List<SysFunction> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysFunction.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }
}
