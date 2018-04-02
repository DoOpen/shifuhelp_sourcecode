package com.project.bean.system;

/**
 * 系统设置
 * @author 彭方林
 * @date 2018年4月2日
 */
public class SettingBean {
	private Integer setting_id;
	private String setting_name;//名称
	private String setting_value;//参数值
	private String setting_desc;//介绍
	private String is_delete;//1删除
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String setting_unit;//设置单位
	public String getSetting_unit() {
		return setting_unit;
	}
	public SettingBean setSetting_unit(String setting_unit) {
		this.setting_unit = setting_unit;
		return this;
	}
	public Integer getSetting_id() {
		return setting_id;
	}
	public SettingBean setSetting_id(Integer setting_id) {
		this.setting_id = setting_id;
		return this;
	}
	public String getSetting_name() {
		return setting_name;
	}
	public SettingBean setSetting_name(String setting_name) {
		this.setting_name = setting_name;
		return this;
	}
	public String getSetting_value() {
		return setting_value;
	}
	public SettingBean setSetting_value(String setting_value) {
		this.setting_value = setting_value;
		return this;
	}
	public String getSetting_desc() {
		return setting_desc;
	}
	public SettingBean setSetting_desc(String setting_desc) {
		this.setting_desc = setting_desc;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public SettingBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public SettingBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public SettingBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
}
