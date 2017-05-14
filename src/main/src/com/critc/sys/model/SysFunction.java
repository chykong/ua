package com.critc.sys.model;

/**
 * 操作
 * 
 * @author chykong
 * 
 */
public class SysFunction {
	private int id;
	private int module_id;//模块id
	private String module_name;//模块名称
	private String name;//名称
	private String code;//代码
	private String url;//url
	private int type;//类型
	private String description;//
	private int display_order;//

	private int checked;//是否选择

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	@Override
	public String toString() {
		return "SysFunction{" +
				"id=" + id +
				", module_id=" + module_id +
				", module_name='" + module_name + '\'' +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", url='" + url + '\'' +
				", type=" + type +
				", description='" + description + '\'' +
				", display_order=" + display_order +
				", checked=" + checked +
				'}';
	}
}
