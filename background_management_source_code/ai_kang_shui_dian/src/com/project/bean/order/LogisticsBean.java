package com.project.bean.order;

public class LogisticsBean {
	private Integer logistics_id;
	private String logistics_name;
	private String logistics_code;
	private String is_delete;
	private Integer sort;
	private String create_time;
	private String update_time;
	public Integer getLogistics_id() {
		return logistics_id;
	}
	public LogisticsBean setLogistics_id(Integer logistics_id) {
		this.logistics_id = logistics_id;
		return this;
	}
	public String getLogistics_name() {
		return logistics_name;
	}
	public LogisticsBean setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
		return this;
	}
	public String getLogistics_code() {
		return logistics_code;
	}
	public LogisticsBean setLogistics_code(String logistics_code) {
		this.logistics_code = logistics_code;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public LogisticsBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public LogisticsBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public LogisticsBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public Integer getSort() {
		return sort;
	}
	public LogisticsBean setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
}
