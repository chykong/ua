package com.critc.sys.model;

import java.util.Date;

/**
 * 用户角色model
 *
 * @author 孔垂云
 */
public class SysRole {

    private int id;// 角色代码
    private String name;// 角色名称
    private String description;//描述
    private int is_delete;//能否删除
    private Date create_date;//创建时间
    private String create_person;//创建人

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreate_person() {
        return create_person;
    }

    public void setCreate_person(String create_person) {
        this.create_person = create_person;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", is_delete=" + is_delete +
                ", create_date=" + create_date +
                ", create_person='" + create_person + '\'' +
                '}';
    }
}
