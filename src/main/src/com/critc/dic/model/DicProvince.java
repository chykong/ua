package com.critc.dic.model;

/**
 * 省
 * 
 * @author lmy
 * 
 */
public class DicProvince {

	private int id;// 编号 主键，自动增长
	private String upper_code; // 上级编码，若为最高级则为0
	private String province_code; // 省编码 唯一编码，系统生成
	private String province_name; // 省名
	private Integer status; // 状态 0：无效；1：有效（默认值）
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

	public String getProvince_code() {
		return province_code;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}


	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
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
