package com.critc.sys.model;

public class SysRoleFunction {

	private int id;
	private int role_id;
	private int function_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getFunction_id() {
		return function_id;
	}

	public void setFunction_id(int function_id) {
		this.function_id = function_id;
	}

	@Override
	public String toString() {
		return "SysRoleFunction{" +
				"id=" + id +
				", role_id=" + role_id +
				", function_id=" + function_id +
				'}';
	}
}
