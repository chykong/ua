package com.critc.util.page;

/**
 * 分页工具类，用于生成分页语句
 *
 * @author 孔垂云
 */
public class PageUtil {

    /**
     * 生成mysql分页查询语句
     *
     * @param sql
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static String createMysqlPageSql(String sql, int pageIndex, int pageSize) {
        return sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
    }

    /**
     * 生成Sqlserver的分页查询语句
     *
     * @param sql
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static String createSqlServerPageSql(String sql, int pageIndex, int pageSize) {
        sql = "select * from (select row_number() over(order by tempcolumn)temprownumber,* from (select top " + pageIndex * pageSize
                + " tempcolumn=0,* from (" + sql + " ) a) t )tt where temprownumber>" + (pageIndex - 1) * pageSize;
        return sql;
    }

    /**
     * 生成oracle分页查询语句
     * @param sql
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static String createOraclePageSQL(String sql, int pageIndex, int pageSize) {
        return "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <=" + pageIndex * pageSize
                + " ) WHERE RN > " + (pageIndex - 1) * pageSize;
    }
}
