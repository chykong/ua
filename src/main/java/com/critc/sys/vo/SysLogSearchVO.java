package com.critc.sys.vo;

import com.critc.util.page.PageSearchVO;

/**
 * 日志管理查询条件
 *
 * @author chykong
 */
public class SysLogSearchVO extends PageSearchVO {
    private Integer user_id;//
    private String s_date;//起始日期
    private String e_date;//终止日期

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getS_date() {
        return s_date;
    }

    public void setS_date(String s_date) {
        this.s_date = s_date;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    @Override
    public String toString() {
        return "SysLogSearchVO{" +
                "user_id=" + user_id +
                ", s_date='" + s_date + '\'' +
                ", e_date='" + e_date + '\'' +
                '}';
    }
}
