package com.project.bean.system;

public class SystemRoleBean {
	private Integer role_id;
	private String role_name;
	private String create_time;
	private String is_delete;
	private String module_ids;
	public Integer getRole_id() {
		return role_id;
	}
	public SystemRoleBean setRole_id(Integer role_id) {
		this.role_id = role_id;
		return this;
	}
	public String getRole_name() {
		return role_name;
	}
	public SystemRoleBean setRole_name(String role_name) {
		this.role_name = role_name;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public SystemRoleBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public SystemRoleBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getModule_ids() {
		return module_ids;
	}
	public SystemRoleBean setModule_ids(String module_ids) {
		this.module_ids = module_ids;
		return this;
	}
}
