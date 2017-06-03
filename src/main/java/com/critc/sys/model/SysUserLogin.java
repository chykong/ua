package com.critc.sys.model;

import java.util.Date;

/**
 * 用户登录信息
 * @author chykong
 *
 */
public class SysUserLogin {
	private int id;//编号
	private int user_id;//用户code
	private Date login_date;//登录时间
	private String login_ip;//登录IP

	private String terminal;//终端
	private String explorerType;//浏览器类型
	private String explorerVersion;//浏览器版本

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Date getLogin_date() {
		return login_date;
	}

	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getExplorerType() {
		return explorerType;
	}

	public void setExplorerType(String explorerType) {
		this.explorerType = explorerType;
	}

	public String getExplorerVersion() {
		return explorerVersion;
	}

	public void setExplorerVersion(String explorerVersion) {
		this.explorerVersion = explorerVersion;
	}

	@Override
	public String toString() {
		return "SysUserLogin{" +
				"id=" + id +
				", user_id=" + user_id +
				", login_date=" + login_date +
				", login_ip='" + login_ip + '\'' +
				", terminal='" + terminal + '\'' +
				", explorerType='" + explorerType + '\'' +
				", explorerVersion='" + explorerVersion + '\'' +
				'}';
	}
}
