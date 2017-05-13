package com.critc.sys.vo;

import com.critc.util.page.PageSearchVO;

/**
 * 用户管理查询条件
 *
 * @author 孔垂云
 */
public class SysUserSearchVO extends PageSearchVO {
    private String username;//email
    private Integer department_id;//部门
    private Integer status;//状态
    private Integer role_id;//角色
    private String realname;//姓名

    //姓名模糊查询
    public String getRealnameStr() {
        return "%" + realname + "%";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "SysUserSearchVO{" +
                "username='" + username + '\'' +
                ", department_id=" + department_id +
                ", status=" + status +
                ", role_id=" + role_id +
                ", realname='" + realname + '\'' +
                '}';
    }
}
