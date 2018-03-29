package com.project.bean.system;

public class SystemAccountBean {
	private Integer account_id;
	private Integer account_login_id;
	private String system_name;
	private String system_account;
	private String system_img;
	private String system_password;
	private String system_old_password;
	private String system_token;
	private String system_type;
	private String system_type_show;
	private Integer role_id;
	private String role_name;
	private String is_delete;
	private String create_time;
	private String update_time;
	private String is_disable;
	private String is_disable_show;
	private Integer relation_id;
	private String district;
	
	public String getDistrict() {
		return district;
	}

	public SystemAccountBean setDistrict(String district) {
		this.district = district;
		return this;
	}

	public Integer getAccount_login_id() {
		return account_login_id;
	}

	public SystemAccountBean setAccount_login_id(Integer account_login_id) {
		this.account_login_id = account_login_id;
		return this;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public SystemAccountBean setAccount_id(Integer account_id) {
		this.account_id = account_id;
		return this;
	}

	public String getSystem_name() {
		return system_name;
	}

	public SystemAccountBean setSystem_name(String system_name) {
		this.system_name = system_name;
		return this;
	}

	public String getSystem_account() {
		return system_account;
	}

	public SystemAccountBean setSystem_account(String system_account) {
		this.system_account = system_account;
		return this;
	}

	public String getSystem_img() {
		return system_img;
	}

	public SystemAccountBean setSystem_img(String system_img) {
		this.system_img = system_img;
		return this;
	}

	public String getSystem_password() {
		return system_password;
	}

	public SystemAccountBean setSystem_password(String system_password) {
		this.system_password = system_password;
		return this;
	}

	public String getSystem_token() {
		return system_token;
	}

	public SystemAccountBean setSystem_token(String system_token) {
		this.system_token = system_token;
		return this;
	}

	public String getSystem_type() {
		return system_type;
	}

	public SystemAccountBean setSystem_type(String system_type) {
		this.system_type = system_type;
		this.system_type_show="system".equals(system_type)?"系统账号":
			"merchants".equals(system_type)?"商家账号":"未知";
		return this;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public SystemAccountBean setRole_id(Integer role_id) {
		this.role_id = role_id;
		return this;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public SystemAccountBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}

	public String getCreate_time() {
		return create_time;
	}

	public SystemAccountBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public SystemAccountBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}

	public String getIs_disable() {
		return is_disable;
	}

	public SystemAccountBean setIs_disable(String is_disable) {
		this.is_disable = is_disable;
		this.is_disable_show="0".equals(is_disable)?"正常":"冻结";
		return this;
	}

	public String getSystem_old_password() {
		return system_old_password;
	}

	public SystemAccountBean setSystem_old_password(String system_old_password) {
		this.system_old_password = system_old_password;
		return this;
	}

	public String getSystem_type_show() {
		return system_type_show;
	}

	public SystemAccountBean setSystem_type_show(String system_type_show) {
		this.system_type_show = system_type_show;
		return this;
	}

	public String getRole_name() {
		return role_name;
	}

	public SystemAccountBean setRole_name(String role_name) {
		this.role_name = role_name;
		return this;
	}

	public String getIs_disable_show() {
		return is_disable_show;
	}

	public SystemAccountBean setIs_disable_show(String is_disable_show) {
		this.is_disable_show = is_disable_show;
		return this;
	}

	public Integer getRelation_id() {
		return relation_id;
	}

	public SystemAccountBean setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
		return this;
	}
}
