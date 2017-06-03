package com.critc.sys.dao;

import com.critc.common.vo.ComboboxVO;
import com.critc.sys.model.SysRole;
import com.critc.sys.model.SysRoleFunction;
import com.critc.sys.model.SysRoleModule;
import com.critc.util.dao.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SysRoleDao extends BaseDao {

    public int add(SysRole sysRole) {
        String sql = "insert into t_sys_role(name,description,create_date,is_delete,create_person)" + " values(:name,:description,now(),:is_delete,:create_person)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rc = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysRole), keyHolder);
        if (rc > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return 0;
        }
    }

    public int update(SysRole sysRole) {
        String sql = "update t_sys_role set name=:name,description=:description where id=:id";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysRole));
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_sys_role where id=? ";
        Object[] params = new Object[]{id};
        return jdbcTemplate.update(sql, params);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SysRole get(int id) {
        String sql = "select * from t_sys_role where id=? ";
        Object[] params = new Object[]{id};
        List<SysRole> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(SysRole.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 角色列表
     *
     * @return
     */
    public List<SysRole> list() {
        String sql = "";
        List<SysRole> list = new ArrayList<>();
        sql = "select t.* from t_sys_role t order by id ";
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SysRole.class));
        return list;
    }

    /**
     * 角色列表
     *
     * @return
     */
    public List<ComboboxVO> listCombo() {
        String sql = "select id value,name content from t_sys_role where 1=1 order by id ";
        List<ComboboxVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComboboxVO.class));
        return list;
    }

    /**
     * 根据角色id获取所有模块
     *
     * @param role_id
     * @return
     */
    public List<SysRoleModule> listRoleModule(int role_id) {
        String sql = "select * from t_sys_rolemodule where role_id=? ";
        Object[] params = new Object[]{role_id};
        List<SysRoleModule> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysRoleModule.class));
        return list;
    }

    /**
     * 删除角色对应的模块
     *
     * @param role_id
     */
    public void deleteRoleModule(int role_id) {
        jdbcTemplate.update("delete from t_sys_rolemodule where role_id=?", role_id);
    }

    /**
     * 新增角色对应模块
     *
     * @param role_id
     * @param module_id
     */
    public void addRoleModule(int role_id, int module_id) {
        String sql = "insert into t_sys_rolemodule(role_id,module_id) values(?,?)";
        Object[] params = new Object[]{role_id, module_id};
        jdbcTemplate.update(sql, params);
    }

    /**
     * 删除角色对应的功能
     *
     * @param role_id
     */
    public void deleteRoleFunction(int role_id) {
        jdbcTemplate.update("delete from t_sys_rolefunction where role_id=?", role_id);
    }

    /**
     * 新增角色对应功能
     *
     * @param role_id
     * @param function_id
     */
    public void addRoleFunction(int role_id, int function_id) {
        String sql = "insert into t_sys_rolefunction(role_id,function_id) values(?,?)";
        Object[] params = new Object[]{role_id, function_id};
        jdbcTemplate.update(sql, params);
    }

    /**
     * 根据角色id获取所有功能
     *
     * @param role_id
     * @return
     */
    public List<SysRoleFunction> listRoleFunction(int role_id) {
        String sql = "select * from t_sys_rolefunction where role_id=? ";
        Object[] params = new Object[]{role_id};
        List<SysRoleFunction> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysRoleFunction.class));
        return list;
    }

}