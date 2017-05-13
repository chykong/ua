package com.critc.dic.model;

/**
 * 县
 * 
 * @author Administrator
 * 
 */
public class DicDistrict {

	private int id;// 编号 主键，自动增长
	private String upper_code;// 上级编码 上级编码，若为最高级则为0
	private String district_code;// 区县编码 唯一编码，系统生成
	private String district_name;// 区县名
	private Integer status;// 状态 0：无效；1：有效（默认值）
	private Integer level;// 等级

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

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
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
