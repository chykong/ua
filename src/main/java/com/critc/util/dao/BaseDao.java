package com.critc.util.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by 孔垂云 on 2017/5/14.
 */
@Component
public class BaseDao {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 获取  NamedParameterJdbcTemplate
     *
     * @return
     */
    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

}
