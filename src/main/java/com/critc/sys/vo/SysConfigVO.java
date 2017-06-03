package com.critc.sys.vo;

public class SysConfigVO {

	private String syskey;
	private String sysvalue;
	private String description;
	private int display_order;
	
	public String getSyskey() {
		return syskey;
	}
	public void setSyskey(String syskey) {
		this.syskey = syskey;
	}
	public String getSysvalue() {
		return sysvalue;
	}
	public void setSysvalue(String sysvalue) {
		this.sysvalue = sysvalue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(int display_order) {
		this.display_order = display_order;
	}

	@Override
	public String toString() {
		return "SysConfigVO{" +
				"syskey='" + syskey + '\'' +
				", sysvalue='" + sysvalue + '\'' +
				", description='" + description + '\'' +
				", display_order=" + display_order +
				'}';
	}
}
