package com.critc.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 操作日志
 * @author chykong
 *
 */
public class SysLog {
	private long id;
	private int user_id;//用户id
	private Date opera_date;//操作日期
	private String opera_ip;//ip地址
	private String module_name;//模块id
	private String opera_name;//操作名称
	private String opera_url;//操作url
	private int opera_type;//操作类型
	private String opera_params;//参数

	private String realname;//用户姓名
	private String user_code;//用户代码

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getOpera_date() {
		return opera_date;
	}

	public void setOpera_date(Date opera_date) {
		this.opera_date = opera_date;
	}

	public String getOpera_ip() {
		return opera_ip;
	}

	public void setOpera_ip(String opera_ip) {
		this.opera_ip = opera_ip;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getOpera_name() {
		return opera_name;
	}

	public void setOpera_name(String opera_name) {
		this.opera_name = opera_name;
	}

	public String getOpera_url() {
		return opera_url;
	}

	public void setOpera_url(String opera_url) {
		this.opera_url = opera_url;
	}

	public int getOpera_type() {
		return opera_type;
	}

	public void setOpera_type(int opera_type) {
		this.opera_type = opera_type;
	}

	public String getOpera_params() {
		return opera_params;
	}

	public void setOpera_params(String opera_params) {
		this.opera_params = opera_params;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	@Override
	public String toString() {
		return "SysLog{" +
				"id=" + id +
				", user_id=" + user_id +
				", opera_date=" + opera_date +
				", opera_ip='" + opera_ip + '\'' +
				", module_name='" + module_name + '\'' +
				", opera_name='" + opera_name + '\'' +
				", opera_url='" + opera_url + '\'' +
				", opera_type=" + opera_type +
				", opera_params='" + opera_params + '\'' +
				", realname='" + realname + '\'' +
				", user_code='" + user_code + '\'' +
				'}';
	}
}
