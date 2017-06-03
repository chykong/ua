package com.critc.sys.vo;

/**
 * 功能模块vo
 * 
 * @author chykong
 * 
 */
public class SysModuleVO {
	private int id;
	private String name;
	private int parent_id;
	private String url;
	private String icon;//
	private String target;//
	private int display_order;//

	private int cnt;
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

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

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getDisplay_order() {
		return display_order;
	}

	public void setDisplay_order(int display_order) {
		this.display_order = display_order;
	}

	@Override
	public String toString() {
		return "SysModuleVO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", parent_id=" + parent_id +
				", url='" + url + '\'' +
				", icon='" + icon + '\'' +
				", target='" + target + '\'' +
				", display_order=" + display_order +
				", cnt=" + cnt +
				'}';
	}
}
