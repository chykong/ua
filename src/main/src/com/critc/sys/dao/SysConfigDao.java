package com.critc.sys.dao;

import com.critc.sys.model.SysConfig;
import com.critc.util.dao.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统配置Dao
 *
 * @author 孔垂云
 */
@Repository
public class SysConfigDao extends BaseDao {

    /**
     * 新增
     *
     * @param sysConfig
     * @return
     */

    public int add(SysConfig sysConfig) {
        String sql = "insert into t_sys_config(syskey,sysvalue,description,display_order) values(:syskey,:sysvalue,:description,:display_order)";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysConfig));
    }

    /**
     * 修改
     *
     * @param sysConfig sysConfig
     * @return 修改值
     */
    public int update(SysConfig sysConfig) {
        String sql = "update t_sys_config set sysvalue=:sysvalue,description=:description,display_order=:display_order where syskey=:syskey ";
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(sysConfig));
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
     * @return
     */
    public List<SysConfig> list() {
        String sql = "select * from t_sys_config where 1=1  order by display_order  ";
        List<SysConfig> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SysConfig.class));
        return list;
    }

    /**
     * 获取参数信息
     *
     * @param syskey
     * @return
     */
    public SysConfig get(String syskey) {
        String sql = "select * from t_sys_config where syskey=?";
        Object[] params = new Object[]{syskey};
        List<SysConfig> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(SysConfig.class));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

}
