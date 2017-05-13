package com.critc.dic.model;
/**
 * 市
 * @author Administrator
 *
 */
public class DicCity {
	private int id;// 编号主键，自动增长
	private String upper_code;// 上级编码，若为最高级则为0
	private String city_code; // 市编码 唯一编码，系统生成
	private String city_name;// 市名
	private Integer status;// 状态 0：无效；1：有效（默认值）
	private Integer level; // 等级

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUpper_code() {
		return upper_code;
	}

	public void setUpper_code(String upper_code) {
		this.upper_code = upper_code;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
